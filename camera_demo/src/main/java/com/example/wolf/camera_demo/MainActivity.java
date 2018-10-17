package com.example.wolf.camera_demo;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Uri uri;
    private static final int REQUEST_CODE_OPEN_CAMERA = 0;
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.takePhotoButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                Log.d(TAG, "Environment.getExternalStorageDirectory(): " + Environment.getExternalStorageDirectory());
                File directory = new File(Environment.getExternalStorageDirectory() + "/CameraDemo");
                if (!directory.exists())
                    Log.d(TAG, "directory.mkdir(): " + directory.mkdir());
                File file = new File(directory + "/IMG" + System.currentTimeMillis() + ".jpg");
                uri = Uri.fromFile(file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);  //将拍摄到的图片存储到uri所指示的路径，此操作会将onActivityResult中的data置为null
                startActivityForResult(intent, REQUEST_CODE_OPEN_CAMERA);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_OPEN_CAMERA) {
//                Bitmap bitmap = (Bitmap) data.getExtras().get("data");  //拍摄到的图片的缩略图
//                Log.d(TAG, "bitmap.getAllocationByteCount(): " + bitmap.getAllocationByteCount());
                Intent intent = PhotoActivity.newIntent(this, uri);
                startActivity(intent);
            }
        }
    }
}