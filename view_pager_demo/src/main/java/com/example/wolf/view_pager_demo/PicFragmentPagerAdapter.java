package com.example.wolf.view_pager_demo;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PicFragmentPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<PicFragment> picFragments;

    public PicFragmentPagerAdapter(FragmentManager fm, ArrayList<PicFragment> picFragments) {
        super(fm);
        this.picFragments = picFragments;
    }

    @Override
    public PicFragment getItem(int i) {
        return picFragments.get(i);
    }

    @Override
    public int getCount() {
        return picFragments.size();
    }
}