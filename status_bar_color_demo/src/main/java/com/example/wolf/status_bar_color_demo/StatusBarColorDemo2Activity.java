package com.example.wolf.status_bar_color_demo;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StatusBarColorDemo2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar_color_demo_2);
        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(Color.RED);
    }
}