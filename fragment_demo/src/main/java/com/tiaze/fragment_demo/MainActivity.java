package com.tiaze.fragment_demo;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout chatsLinearLayout, contactsLinearLayout, discoverLinearLayout, meLinearLayout, currentTab;
    private FragmentManager fragmentManager;
    private String currentFragmentTag;
    private static final String KEY_FRAGMENT_TAG = "FragmentTag";
    private static final String TAG_CHATS_FRAGMENT = "ChatsFragment";
    private static final String TAG_CONTACTS_FRAGMENT = "ContactsFragment";
    private static final String TAG_DISCOVER_FRAGMENT = "DiscoverFragment";
    private static final String TAG_ME_FRAGMENT = "MeFragment";
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StatusBarColorSetter.setStatusBarColor(this, getResources().getColor(R.color.colorTitleBar));
        chatsLinearLayout = findViewById(R.id.chatsLinearLayout);
        chatsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(ChatsFragment.getInstance(), TAG_CHATS_FRAGMENT);
            }
        });
        contactsLinearLayout = findViewById(R.id.contactsLinearLayout);
        contactsLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(ContactsFragment.getInstance(), TAG_CONTACTS_FRAGMENT);
            }
        });
        discoverLinearLayout = findViewById(R.id.discoverLinearLayout);
        discoverLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(DiscoverFragment.getInstance(), TAG_DISCOVER_FRAGMENT);
            }
        });
        meLinearLayout = findViewById(R.id.meLinearLayout);
        meLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(MeFragment.getInstance(), TAG_ME_FRAGMENT);
            }
        });
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            ChatsFragment chatsFragment = ChatsFragment.getInstance();
            fragmentManager.beginTransaction().add(R.id.fragmentContainerFrameLayout, chatsFragment, TAG_CHATS_FRAGMENT).commit();
            currentFragmentTag = TAG_CHATS_FRAGMENT;
            currentTab = chatsLinearLayout;
        } else {
            currentFragmentTag = savedInstanceState.getString(KEY_FRAGMENT_TAG);
            switch (currentFragmentTag) {
                case TAG_CHATS_FRAGMENT:
                    currentTab = chatsLinearLayout;
                    break;
                case TAG_CONTACTS_FRAGMENT:
                    currentTab = contactsLinearLayout;
                    break;
                case TAG_DISCOVER_FRAGMENT:
                    currentTab = discoverLinearLayout;
                    break;
                default:
                    currentTab = meLinearLayout;
            }
        }
        currentTab.setBackgroundColor(getResources().getColor(R.color.colorSelectedTab));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_FRAGMENT_TAG, currentFragmentTag);
    }

    private void switchTab(String targetFragmentTag) {
        LinearLayout targetTab;
        switch (targetFragmentTag) {
            case TAG_CHATS_FRAGMENT:
                targetTab = chatsLinearLayout;
                break;
            case TAG_CONTACTS_FRAGMENT:
                targetTab = contactsLinearLayout;
                break;
            case TAG_DISCOVER_FRAGMENT:
                targetTab = discoverLinearLayout;
                break;
            default:
                targetTab = meLinearLayout;
        }
        Resources resources = getResources();
        currentTab.setBackgroundColor(resources.getColor(R.color.colorUnselectedTab));
        targetTab.setBackgroundColor(resources.getColor(R.color.colorSelectedTab));
        currentTab = targetTab;
    }

    private void switchFragment(Fragment targetFragment, String targetFragmentTag) {
        switchTab(targetFragmentTag);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment currentFragment;
        switch (currentFragmentTag) {
            case TAG_CHATS_FRAGMENT:
                currentFragment = ChatsFragment.getInstance();
                break;
            case TAG_CONTACTS_FRAGMENT:
                currentFragment = ContactsFragment.getInstance();
                break;
            case TAG_DISCOVER_FRAGMENT:
                currentFragment = DiscoverFragment.getInstance();
                break;
            default:
                currentFragment = MeFragment.getInstance();
        }
        fragmentTransaction.hide(currentFragment);
        if (targetFragment.isAdded())
            fragmentTransaction.show(targetFragment);
        else
            fragmentTransaction.add(R.id.fragmentContainerFrameLayout, targetFragment, targetFragmentTag);
        fragmentTransaction.commit();
        currentFragmentTag = targetFragmentTag;
        Log.d(TAG, "fragmentManager.getFragments().size(): " + fragmentManager.getFragments().size());
    }
}