package com.example.wolf.status_bar_color_demo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class StatusBarColorDemo1Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView titleBarTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar_color_demo_1);
        StatusBarColorSetter.setStatusBarColor(this, Color.RED);
        titleBarTextView = findViewById(R.id.titleBarTextView);
        findViewById(R.id.redButton).setOnClickListener(this);
        findViewById(R.id.greenButton).setOnClickListener(this);
        findViewById(R.id.blueButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.redButton:
                StatusBarColorSetter.setStatusBarColor(this, Color.RED);
                titleBarTextView.setBackgroundColor(Color.RED);
                break;
            case R.id.greenButton:
                StatusBarColorSetter.setStatusBarColor(this, Color.GREEN);
                titleBarTextView.setBackgroundColor(Color.GREEN);
                break;
            case R.id.blueButton:
                StatusBarColorSetter.setStatusBarColor(this, Color.BLUE);
                titleBarTextView.setBackgroundColor(Color.BLUE);
        }
    }
}