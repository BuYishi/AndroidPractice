package com.example.wolf.status_bar_color_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.settingMethod1Button).setOnClickListener(this);
        findViewById(R.id.settingMethod2Button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settingMethod1Button:
                startActivity(new Intent(this, StatusBarColorDemo1Activity.class));
                break;
            case R.id.settingMethod2Button:
                startActivity(new Intent(this, StatusBarColorDemo2Activity.class));
        }
    }
}