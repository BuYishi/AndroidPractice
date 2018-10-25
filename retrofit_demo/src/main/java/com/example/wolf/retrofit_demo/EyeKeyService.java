package com.example.wolf.retrofit_demo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EyeKeyService {
    String BASE_URL = "https://api.eyekey.com/";

    @POST("face/Check/checking")
    Call<FaceDetectionResponse> detectFace(@Query("app_id") String appId, @Query("app_key") String appKey, @Query("url") String imageUrl);

    @GET("face/Match/match_compare")
    Call<FaceComparisonResponse> compareFaces(@Query("app_id") String appId, @Query("app_key") String appKey,
                                              @Query("face_id1") String faceId1, @Query("face_id2") String faceId2);
}