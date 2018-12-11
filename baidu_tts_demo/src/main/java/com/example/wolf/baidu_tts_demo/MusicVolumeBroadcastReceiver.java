package com.example.wolf.baidu_tts_demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;

public abstract class MusicVolumeBroadcastReceiver extends BroadcastReceiver {
    public static final String ACTION_VOLUME_CHANGED = "android.media.VOLUME_CHANGED_ACTION";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(ACTION_VOLUME_CHANGED)) {
            AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
            int index = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            onVolumeChanged(index);
        }
    }

    public abstract void onVolumeChanged(int index);
}