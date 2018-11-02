package com.tiaze.fragment_demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ChatsFragment extends Fragment {
    private String chatsId;
    private static ChatsFragment instance;

    public ChatsFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatsId = System.currentTimeMillis() % 10000 + "";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chats, container, false);
        TextView chatsTextView = view.findViewById(R.id.chatsTextView);
        chatsTextView.append('\n' + chatsId);
        return view;
    }

    public static ChatsFragment getInstance() {
        if (instance == null)
            instance = new ChatsFragment();
        return instance;
    }
}