package com.cbwit.genius_challenge.Features.Service;

import com.cbwit.genius_challenge.Features.Model.POJO.APIResponse;
import com.cbwit.genius_challenge.Features.Model.POJO.NewUserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {


    @GET("users")
    Call<APIResponse> getResponse(
            @Query("page") int page
    );

    @POST("post")
    Call<NewUserResponse> createPost(
            @Body NewUserResponse post
    );

}
