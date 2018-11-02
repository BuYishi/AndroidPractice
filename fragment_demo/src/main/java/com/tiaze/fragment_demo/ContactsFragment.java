package com.tiaze.fragment_demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class ContactsFragment extends Fragment {
    private Button contactsButton;
    private String contactsId;
    private static ContactsFragment instance;

    public ContactsFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contactsId = System.currentTimeMillis() % 10000 + "";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        contactsButton = view.findViewById(R.id.contactsButton);
        //在此不能使用contactsButton.append( '\n' + contactsId)，否则应用将崩溃。应该是Android SDK的一个bug
        contactsButton.setText(contactsButton.getText() + "\n" + contactsId);
        contactsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), contactsButton.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public static ContactsFragment getInstance() {
        if (instance == null)
            instance = new ContactsFragment();
        return instance;
    }
}