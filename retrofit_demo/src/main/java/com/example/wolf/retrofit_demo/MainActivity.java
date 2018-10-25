package com.example.wolf.retrofit_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private EyeKeyService eyeKeyService;
    private static final String APP_ID = "f89ae61fd63d4a63842277e9144a6bd2", APP_KEY = "af1cd33549c54b27ae24aeb041865da2";
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.synchronousPostTestButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            String imageUrl = "https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=321851cefc246b607b0eb572d3c37d71/9345d688d43f879428d347b3d81b0ef41bd53a7a.jpg";
                            FaceDetectionResponse faceDetectionResponse = eyeKeyService.detectFace(APP_ID, APP_KEY, imageUrl).execute().body();
                            final String responseCode = faceDetectionResponse.getRes_code();
                            final int width = faceDetectionResponse.getImg_width(), height = faceDetectionResponse.getImg_height();
                            final FaceDetectionResponse.Face face = faceDetectionResponse.getFaces().get(0);
                            final String faceId = face.getFace_id();
                            Log.d(TAG, "faceId: " + faceId);
                            final FaceDetectionResponse.Face.Attribute attribute = face.getAttribute();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText("Synchronous Post Test:");
                                    textView.append("\n回应码：" + responseCode);
                                    textView.append("\n图像宽度：" + width);
                                    textView.append("\n图像高度：" + height);
                                    textView.append("\nFace id: " + faceId);
                                    textView.append("\n性别：" + attribute.getGender());
                                    textView.append("\n年龄：" + attribute.getAge());
                                }
                            });
                        } catch (IOException ex) {
                            Log.e(TAG, "ex: " + ex);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "网络异常，请检查网络", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }.start();
            }
        });
        findViewById(R.id.asynchronousPostTestButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String imageUrl = "http://vpic.video.qq.com/96487261/p0331w01oiv_ori_3.jpg";
                eyeKeyService.detectFace(APP_ID, APP_KEY, imageUrl).enqueue(new Callback<FaceDetectionResponse>() {
                    @Override
                    public void onResponse(Call<FaceDetectionResponse> call, Response<FaceDetectionResponse> response) {
                        FaceDetectionResponse faceDetectionResponse = response.body();
                        String responseCode = faceDetectionResponse.getRes_code();
                        int width = faceDetectionResponse.getImg_width(), height = faceDetectionResponse.getImg_height();
                        FaceDetectionResponse.Face face = faceDetectionResponse.getFaces().get(0);
                        String faceId = face.getFace_id();
                        Log.d(TAG, "faceId: " + faceId);
                        FaceDetectionResponse.Face.Attribute attribute = face.getAttribute();
                        textView.setText("Asynchronous Post Test:");
                        textView.append("\n回应码：" + responseCode);
                        textView.append("\n图像宽度：" + width);
                        textView.append("\n图像高度：" + height);
                        textView.append("\nFace id: " + faceId);
                        textView.append("\n性别：" + attribute.getGender());
                        textView.append("\n年龄：" + attribute.getAge());
                    }

                    @Override
                    public void onFailure(Call<FaceDetectionResponse> call, Throwable t) {
                        Log.e(TAG, "t: " + t);
                        Toast.makeText(MainActivity.this, "网络异常，请检查网络", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        findViewById(R.id.synchronousGetTestButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        String faceId1 = "bc7d6c540db344df903e3a3e2cad88ba", faceId2 = "4038c634aadb4905b4798fee98eb5fb3";
                        try {
                            FaceComparisonResponse faceComparisonResponse = eyeKeyService.compareFaces(APP_ID, APP_KEY, faceId1, faceId2).execute().body();
                            final String responseCode = faceComparisonResponse.getRes_code();
                            final float similarity = faceComparisonResponse.getSimilarity();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText("Synchronous Get Test:");
                                    textView.append("\n回应码：" + responseCode);
                                    textView.append("\n相似度：" + similarity);
                                }
                            });
                        } catch (IOException ex) {
                            Log.e(TAG, "ex: " + ex);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "网络异常，请检查网络", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }.start();
            }
        });
        findViewById(R.id.asynchronousGetTestButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String faceId1 = "bc7d6c540db344df903e3a3e2cad88ba", faceId2 = "4038c634aadb4905b4798fee98eb5fb3";
                eyeKeyService.compareFaces(APP_ID, APP_KEY, faceId1, faceId2).enqueue(new Callback<FaceComparisonResponse>() {
                    @Override
                    public void onResponse(Call<FaceComparisonResponse> call, Response<FaceComparisonResponse> response) {
                        FaceComparisonResponse faceComparisonResponse = response.body();
                        String responseCode = faceComparisonResponse.getRes_code();
                        float similarity = faceComparisonResponse.getSimilarity();
                        textView.setText("Asynchronous Get Test:");
                        textView.append("\n回应码：" + responseCode);
                        textView.append("\n相似度：" + similarity);
                    }

                    @Override
                    public void onFailure(Call<FaceComparisonResponse> call, Throwable t) {
                        Log.e(TAG, "t: " + t);
                        Toast.makeText(MainActivity.this, "网络异常，请检查网络", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        textView = findViewById(R.id.textView);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(EyeKeyService.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        eyeKeyService = retrofit.create(EyeKeyService.class);
    }
}