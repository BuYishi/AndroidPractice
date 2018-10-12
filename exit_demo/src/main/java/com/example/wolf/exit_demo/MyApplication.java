package com.example.wolf.exit_demo;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class MyApplication extends Application {
    private ArrayList<AppCompatActivity> activities = new ArrayList<>();
    private static final String TAG = MyApplication.class.getName();

    public void addActivity(AppCompatActivity activity) {
        activities.add(activity);
    }

    public void removeActivity(AppCompatActivity activity) {
        activities.remove(activity);
    }

    public void finish() {
        int activitiesSize = activities.size();
        Log.d(TAG, "activitiesSize: " + activitiesSize);
        for (int i = 0; i < activitiesSize; ++i) {
            AppCompatActivity activity = activities.get(i);
            activity.finish();
        }
    }
}