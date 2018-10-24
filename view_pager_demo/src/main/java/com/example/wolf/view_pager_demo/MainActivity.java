package com.example.wolf.view_pager_demo;

import android.content.res.Resources;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView dakongyiTextView, rixiangTextView, ruolinTextView;
    private View dakongyiUnderline, rixiangUnderline, ruolinUnderline;
    private ViewPager picViewPager;
    private static final int ITEM_DA_KONG_YI = 0;
    private static final int ITEM_RI_XIANG = 1;
    private static final int ITEM_RUO_LIN_YUAN_SAN = 2;
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarColorSetter.setStatusBarColor(this, getResources().getColor(R.color.colorTitleBar));
        dakongyiTextView = findViewById(R.id.dakongyiTextView);
        rixiangTextView = findViewById(R.id.rixiangTextView);
        ruolinTextView = findViewById(R.id.ruolinTextView);
        dakongyiUnderline = findViewById(R.id.dakongyiUnderline);
        rixiangUnderline = findViewById(R.id.rixiangUnderline);
        ruolinUnderline = findViewById(R.id.ruolinUnderline);
//        int selectedColor = getResources().getColor(R.color.colorUnderlineSelected);
//        dakongyiTextView.setTextColor(selectedColor);
//        dakongyiUnderline.setBackgroundColor(selectedColor);
        findViewById(R.id.dakongyiTab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picViewPager.setCurrentItem(ITEM_DA_KONG_YI);
            }
        });
        findViewById(R.id.rixiangTab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picViewPager.setCurrentItem(ITEM_RI_XIANG);
            }
        });
        findViewById(R.id.ruolinTab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picViewPager.setCurrentItem(ITEM_RUO_LIN_YUAN_SAN);
            }
        });
        picViewPager = findViewById(R.id.picViewPager);
        ArrayList<PicFragment> picFragments = new ArrayList<>();
        picFragments.add(PicFragment.newInstance(R.mipmap.dakongyi));  //每次执行onCreate后都会新建PicFragment实例，须优化
        picFragments.add(PicFragment.newInstance(R.mipmap.rixiang));
        picFragments.add(PicFragment.newInstance(R.mipmap.ruolinyuansan));
        FragmentManager fragmentManager = getSupportFragmentManager();
        picViewPager.setAdapter(new PicFragmentPagerAdapter(fragmentManager, picFragments));
        picViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                Resources resources = getResources();
                int selectedColor = resources.getColor(R.color.colorUnderlineSelected);
                int underlineUnselectedColor = resources.getColor(R.color.colorUnderlineUnselected);
                int textUnselectedColor = resources.getColor(R.color.black);
                switch (i) {
                    case ITEM_DA_KONG_YI:
                        dakongyiTextView.setTextColor(selectedColor);
                        dakongyiUnderline.setBackgroundColor(selectedColor);
                        rixiangTextView.setTextColor(textUnselectedColor);
                        rixiangUnderline.setBackgroundColor(underlineUnselectedColor);
                        ruolinTextView.setTextColor(textUnselectedColor);
                        ruolinUnderline.setBackgroundColor(underlineUnselectedColor);
                        break;
                    case ITEM_RI_XIANG:
                        rixiangTextView.setTextColor(selectedColor);
                        rixiangUnderline.setBackgroundColor(selectedColor);
                        dakongyiTextView.setTextColor(textUnselectedColor);
                        dakongyiUnderline.setBackgroundColor(underlineUnselectedColor);
                        ruolinTextView.setTextColor(textUnselectedColor);
                        ruolinUnderline.setBackgroundColor(underlineUnselectedColor);
                        break;
                    case ITEM_RUO_LIN_YUAN_SAN:
                        ruolinTextView.setTextColor(selectedColor);
                        ruolinUnderline.setBackgroundColor(selectedColor);
                        dakongyiTextView.setTextColor(textUnselectedColor);
                        dakongyiUnderline.setBackgroundColor(underlineUnselectedColor);
                        rixiangTextView.setTextColor(textUnselectedColor);
                        rixiangUnderline.setBackgroundColor(underlineUnselectedColor);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        Log.d(TAG, "fragmentManager.getFragments().size(): " + fragmentManager.getFragments().size());
    }
}