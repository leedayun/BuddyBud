package com.swe.buddybud.board;

import com.swe.buddybud.user.UserApiData;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BoardApiService {
    @POST("/board")
    Call<BoardApiData> insertBoard(@Body RequestBody requestBody);

    @PUT("/board/{boardId}")
    Call<BoardApiData> updateBoard(@Path("boardId") Integer boardId, @Body RequestBody requestBody);

    @DELETE("/board/{boardId}")
    Call<BoardApiData> deleteBoard(@Path("boardId") Integer boardId);
}
