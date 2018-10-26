package com.example.wolf.retrofit_demo;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
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
                String imageUrl = "https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/w%3D268%3Bg%3D0/sign=321851cefc246b607b0eb572d3c37d71/9345d688d43f879428d347b3d81b0ef41bd53a7a.jpg";
                final Call<FaceDetectionResponse> call = eyeKeyService.detectFace(APP_ID, APP_KEY, imageUrl);
                final ProgressDialog dialog = showProgressDialog(call);
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            FaceDetectionResponse faceDetectionResponse = call.execute().body();
                            final String responseCode = faceDetectionResponse.getRes_code();
                            final int width = faceDetectionResponse.getImg_width(), height = faceDetectionResponse.getImg_height();
                            final FaceDetectionResponse.Face face = faceDetectionResponse.getFaces().get(0);
                            final String faceId = face.getFace_id();
                            Log.d(TAG, "faceId: " + faceId);
                            final FaceDetectionResponse.Face.Attribute attribute = face.getAttribute();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.dismiss();
                                    textView.setText("Synchronous Post Test:");
                                    textView.append("\n回应码：" + responseCode);
                                    textView.append("\n图像宽度：" + width);
                                    textView.append("\n图像高度：" + height);
                                    textView.append("\nFace id: " + faceId);
                                    textView.append("\n性别：" + attribute.getGender());
                                    textView.append("\n年龄：" + attribute.getAge());
                                }
                            });
                        } catch (final IOException ex) {
                            Log.e(TAG, "ex: " + ex);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    reportException(ex, dialog);
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
                Call<FaceDetectionResponse> call = eyeKeyService.detectFace(APP_ID, APP_KEY, imageUrl);
                final ProgressDialog dialog = showProgressDialog(call);
                call.enqueue(new Callback<FaceDetectionResponse>() {
                    @Override
                    public void onResponse(Call<FaceDetectionResponse> call, Response<FaceDetectionResponse> response) {
                        dialog.dismiss();
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
                        reportException(t, dialog);
                    }
                });
            }
        });
        findViewById(R.id.synchronousGetTestButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String faceId1 = "bc7d6c540db344df903e3a3e2cad88ba", faceId2 = "4038c634aadb4905b4798fee98eb5fb3";
                final Call<FaceComparisonResponse> call = eyeKeyService.compareFaces(APP_ID, APP_KEY, faceId1, faceId2);
                final ProgressDialog dialog = showProgressDialog(call);
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            FaceComparisonResponse faceComparisonResponse = call.execute().body();
                            final String responseCode = faceComparisonResponse.getRes_code();
                            final float similarity = faceComparisonResponse.getSimilarity();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    dialog.dismiss();
                                    textView.setText("Synchronous Get Test:");
                                    textView.append("\n回应码：" + responseCode);
                                    textView.append("\n相似度：" + similarity);
                                }
                            });
                        } catch (final IOException ex) {
                            Log.e(TAG, "ex: " + ex);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    reportException(ex, dialog);
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
                Call<FaceComparisonResponse> call = eyeKeyService.compareFaces(APP_ID, APP_KEY, faceId1, faceId2);
                final ProgressDialog dialog = showProgressDialog(call);
                call.enqueue(new Callback<FaceComparisonResponse>() {
                    @Override
                    public void onResponse(Call<FaceComparisonResponse> call, Response<FaceComparisonResponse> response) {
                        dialog.dismiss();
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
                        reportException(t, dialog);
                    }
                });
            }
        });
        textView = findViewById(R.id.textView);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS).build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(EyeKeyService.BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient).build();
        eyeKeyService = retrofit.create(EyeKeyService.class);
    }

    private ProgressDialog showProgressDialog(final Call call) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("正在加载");
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Log.d(TAG, "onCancel(DialogInterface) called");
                call.cancel();
            }
        });
        dialog.show();
        return dialog;
    }

    private void reportException(Throwable t, ProgressDialog dialog) {
        if (t instanceof SocketTimeoutException) {
            dialog.dismiss();
            Toast.makeText(MainActivity.this, "请求超时，请检查网络", Toast.LENGTH_SHORT).show();
        } else if (t instanceof SocketException) {
            Toast.makeText(MainActivity.this, "已取消", Toast.LENGTH_SHORT).show();
        } else if (t instanceof UnknownHostException)
            Toast.makeText(MainActivity.this, "网络异常，请检查网络", Toast.LENGTH_SHORT).show();
    }
}