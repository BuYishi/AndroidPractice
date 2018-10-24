package com.example.wolf.view_pager_demo;

import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ViewPager picViewPager;
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarColorSetter.setStatusBarColor(this, getResources().getColor(R.color.colorTitleBar));
        findViewById(R.id.dakongyiTab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picViewPager.setCurrentItem(0);
            }
        });
        findViewById(R.id.rixiangTab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picViewPager.setCurrentItem(1);
            }
        });
        findViewById(R.id.ruolinTab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picViewPager.setCurrentItem(2);
            }
        });
        picViewPager = findViewById(R.id.picViewPager);
        ArrayList<PicFragment> picFragments = new ArrayList<>();
        picFragments.add(PicFragment.newInstance(R.mipmap.dakongyi));
        picFragments.add(PicFragment.newInstance(R.mipmap.rixiang));
        picFragments.add(PicFragment.newInstance(R.mipmap.ruolinyuansan));
        FragmentManager fragmentManager = getSupportFragmentManager();
        picViewPager.setAdapter(new PicFragmentPagerAdapter(fragmentManager, picFragments));
        Log.d(TAG, "fragmentManager.getFragments().size(): " + fragmentManager.getFragments().size());
    }
}