package com.example.wolf.view_pager_demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class PicFragment extends Fragment {
    private int mipmapResId;

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

    public static PicFragment newInstance(int mipmapResId) {
        PicFragment picFragment = new PicFragment();
        picFragment.mipmapResId = mipmapResId;
        return picFragment;
    }
}