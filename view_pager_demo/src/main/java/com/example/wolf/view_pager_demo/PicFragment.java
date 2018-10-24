package com.example.wolf.view_pager_demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class PicFragment extends Fragment {
    private int mipmapResId;
    private static ArrayList<PicFragment> picFragments;

    public PicFragment() {
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pic, container, false);
        ImageView picImageView = view.findViewById(R.id.picImageView);
        picImageView.setImageResource(mipmapResId);
        return view;
    }

    private static PicFragment newInstance(int mipmapResId) {
        PicFragment picFragment = new PicFragment();
        picFragment.mipmapResId = mipmapResId;
        return picFragment;
    }

    public static ArrayList<PicFragment> getPicFragments() {
        if (picFragments == null) {
            picFragments = new ArrayList<>();
            picFragments.add(PicFragment.newInstance(R.mipmap.dakongyi));
            picFragments.add(PicFragment.newInstance(R.mipmap.rixiang));
            picFragments.add(PicFragment.newInstance(R.mipmap.ruolinyuansan));
        }
        return picFragments;
    }
}