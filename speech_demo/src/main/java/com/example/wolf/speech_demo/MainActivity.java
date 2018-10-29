package com.example.wolf.speech_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText textToSpeakEditText;
    private BaiduSpeechSynthesizer baiduSpeechSynthesizer;
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((RadioGroup)findViewById(R.id.voiceTypeRadioGroup)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.maleVoiceRadioButton:
                        break;
                    case R.id.femaleVoiceRadioButton:
                        break;
                    case R.id.duXiaoyaoRadioButton:
                        break;
                    case R.id.duYayaRadioButton:
                }
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