package com.example.wolf.speech_demo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Context currentContext = this;
    private EditText textToSpeakEditText;
    private SeekBar volumeSeekBar;
    private BaiduSpeechSynthesizer baiduSpeechSynthesizer;
    private AudioManager audioManager;
    private MusicVolumeBroadcastReceiver musicVolumeBroadcastReceiver = new MusicVolumeBroadcastReceiver() {
        @Override
        public void onVolumeChanged(int index) {
            volumeSeekBar.setProgress(index);
        }
    };
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.rootLinearLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                manager.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });
        ((RadioGroup) findViewById(R.id.voiceTypeRadioGroup)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, final int checkedId) {
                final ProgressDialog dialog = new ProgressDialog(currentContext);  //百度提供的声音切换接口存在延时，会阻塞界面，加进度对话框可以提高用户体验
                dialog.setCancelable(false);
                dialog.setMessage("正在切换声音类型");
                dialog.show();
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        int errorCode = 0;
                        switch (checkedId) {
                            case R.id.maleVoiceRadioButton:
                                errorCode = baiduSpeechSynthesizer.switchVoice(BaiduSpeechSynthesizer.VoiceType.MALE_VOICE);
                                break;
                            case R.id.femaleVoiceRadioButton:
                                errorCode = baiduSpeechSynthesizer.switchVoice(BaiduSpeechSynthesizer.VoiceType.FEMALE_VOICE);
                                break;
                            case R.id.duXiaoyaoRadioButton:
                                errorCode = baiduSpeechSynthesizer.switchVoice(BaiduSpeechSynthesizer.VoiceType.DU_XIAO_YAO);
                                break;
                            case R.id.duYayaRadioButton:
                                errorCode = baiduSpeechSynthesizer.switchVoice(BaiduSpeechSynthesizer.VoiceType.DU_YA_YA);
                        }
                        Log.d(TAG, "errorCode: " + errorCode);
                        dialog.dismiss();
                    }
                }.start();
            }
        });
        findViewById(R.id.speakButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String text = textToSpeakEditText.getText().toString();
                    if (text.isEmpty())
                        text = "百度语音演示";
                    int errorCode = baiduSpeechSynthesizer.speak(text);
                    Log.d(TAG, "errorCode: " + errorCode);
                } catch (BaiduSpeechSynthesizer.UninitializedException ex) {
                    Log.e(TAG, null, ex);
                    Toast.makeText(currentContext, "语音模块未初始化", Toast.LENGTH_SHORT).show();
                }
            }
        });
        volumeSeekBar = findViewById(R.id.volumeSeekBar);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volumeSeekBar.setMax(maxVolume);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volumeSeekBar.setProgress(currentVolume);
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(TAG, "progress: " + progress);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_SHOW_UI | AudioManager.FLAG_PLAY_SOUND);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        textToSpeakEditText = findViewById(R.id.textToSpeakEditText);
        IntentFilter filter = new IntentFilter(MusicVolumeBroadcastReceiver.ACTION_VOLUME_CHANGED);
        registerReceiver(musicVolumeBroadcastReceiver, filter);
        try {
            baiduSpeechSynthesizer = BaiduSpeechSynthesizer.getInstance();
            baiduSpeechSynthesizer.initialize(currentContext);
        } catch (BaiduSpeechSynthesizer.InitializationException ex) {
            Log.e(TAG, "Error code: " + ex.getErrorCode(), ex);
            Toast.makeText(currentContext, "语音模块初始化异常，错误码：" + ex.getErrorCode(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(musicVolumeBroadcastReceiver);
        int releaseErrorCode = baiduSpeechSynthesizer.release();
        Log.d(TAG, "releaseErrorCode: " + releaseErrorCode);
    }
}