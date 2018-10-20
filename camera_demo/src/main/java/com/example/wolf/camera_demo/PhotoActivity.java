package com.example.wolf.camera_demo;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.FileNotFoundException;

public class PhotoActivity extends AppCompatActivity {
    private ImageView photoImageView;
    private static final String EXTRA_PHOTO_URI = "com.example.wolf.camera_demo.PhotoUri";
    private static final String TAG = PhotoActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        photoImageView = findViewById(R.id.photoImageView);
        try {
            Uri uri = getIntent().getParcelableExtra(EXTRA_PHOTO_URI);
            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
            photoImageView.setImageBitmap(bitmap);
            Log.d(TAG, "bitmap.getAllocationByteCount(): " + bitmap.getAllocationByteCount());
        } catch (FileNotFoundException ex) {
            Log.e(TAG, null, ex);
        }
    }

    public static Intent newIntent(Context packageContext, Uri uri) {
        Intent intent = new Intent(packageContext, PhotoActivity.class);
        intent.putExtra(EXTRA_PHOTO_URI, uri);
        return intent;
    }
}