package com.example.wolf.speech_demo;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Context currentContext = this;
    private EditText textToSpeakEditText;
    private BaiduSpeechSynthesizer baiduSpeechSynthesizer;
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                if (baiduSpeechSynthesizer.isInitialized()) {
                    String text = textToSpeakEditText.getText().toString();
                    if (text.isEmpty())
                        text = "百度语音演示";
                    int errorCode = baiduSpeechSynthesizer.speak(text);
                    Log.d(TAG, "errorCode: " + errorCode);
                } else {
                    Toast.makeText(MainActivity.this, "未初始化语音模块", Toast.LENGTH_SHORT).show();
                }
            }
        });
        textToSpeakEditText = findViewById(R.id.textToSpeakEditText);
        try {
            baiduSpeechSynthesizer = BaiduSpeechSynthesizer.getInstance();
            baiduSpeechSynthesizer.initialize(this);
        } catch (BaiduSpeechSynthesizer.InitializationException ex) {
            Log.e(TAG, "Error code: " + ex.getErrorCode(), ex);
            Toast.makeText(this, "语音模块初始化异常，错误码：" + ex.getErrorCode(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        int releaseErrorCode = baiduSpeechSynthesizer.release();
        Log.d(TAG, "releaseErrorCode: " + releaseErrorCode);
    }
}