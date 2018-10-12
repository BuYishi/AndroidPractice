package com.example.wolf.exit_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {
    private Toast toast;
    private MyApplication myApplication;
    private long lastPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApplication = (MyApplication) getApplication();
        myApplication.addActivity(this);
        toast = Toast.makeText(this, "再次点击返回键退出应用", Toast.LENGTH_SHORT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myApplication.removeActivity(this);
    }

    @Override
    public void onBackPressed() {
        long currentPressedTime = System.currentTimeMillis();
        if (currentPressedTime - lastPressedTime > 3000) {
            toast.show();
            lastPressedTime = currentPressedTime;
        } else {
            toast.cancel();
            myApplication.finish();
        }
    }
}