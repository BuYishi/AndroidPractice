package com.example.wolf.status_bar_color_demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class StatusBarColorDemo1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar_color_demo_1);
        StatusBarColorSetter.setStatusBarColor(this, Color.RED);
    }
}