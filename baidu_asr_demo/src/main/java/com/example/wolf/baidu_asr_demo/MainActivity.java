package com.example.wolf.baidu_asr_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String APP_ID = "14616036";
    private static final String API_KEY = "QLnlpsVlFBxZgzsC2MqPe417";
    private static final String SECRET_KEY = "UUZjphFcs0xNH5yyBkj2QZH0I3zG7dtG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.recordButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}