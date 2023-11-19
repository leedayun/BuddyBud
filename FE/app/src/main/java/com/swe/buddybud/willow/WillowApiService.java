package com.swe.buddybud.willow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WillowApiService {
    @GET("/hi")
    Call<WillowApiData> getUserData(@Query("uid") String uid);
}
