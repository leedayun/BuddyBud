package com.swe.buddybud.account;

import com.swe.buddybud.user.UserApiData;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AccountApiService {
    @GET("/account/{userId}/post")
    Call<AccountApiData> getUserPosts(@Path("userId") String userId);

    @GET("/account/{userId}/scrap")
    Call<AccountApiData> getUserScraps(@Path("userId") String userId);

    @PUT("/account/{userId}")
    Call<Void> updateUserAccount(@Path("userId") String userId, @Body UserAccountUpdate userAccountUpdate);
}
