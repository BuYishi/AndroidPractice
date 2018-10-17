package com.example.wolf.camera_demo;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraDevice;
import android.view.SurfaceView;

public class CameraSurfaceView extends SurfaceView {
    private CameraDevice cameraDevice;
    public CameraSurfaceView(Context context) {
        super(context);
    }
}
