package com.example.wolf.status_bar_color_demo;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StatusBarColorDemo2Activity extends AppCompatActivity implements View.OnClickListener {
    private TextView titleBarTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_bar_color_demo_2);
        setStatusBarColor(Color.RED);
        titleBarTextView = findViewById(R.id.titleBarTextView);
        findViewById(R.id.redButton).setOnClickListener(this);
        findViewById(R.id.greenButton).setOnClickListener(this);
        findViewById(R.id.blueButton).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.redButton:
                setStatusBarColor(Color.RED);
                titleBarTextView.setBackgroundColor(Color.RED);
                break;
            case R.id.greenButton:
                setStatusBarColor(Color.GREEN);
                titleBarTextView.setBackgroundColor(Color.GREEN);
                break;
            case R.id.blueButton:
                setStatusBarColor(Color.BLUE);
                titleBarTextView.setBackgroundColor(Color.BLUE);
        }
    }

    private void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= 21)
            getWindow().setStatusBarColor(color);
    }
}