package com.cbwit.genius_challenge.Features.Network;

import android.util.Log;

import com.cbwit.genius_challenge.Features.Service.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit;
    private static final String BASE_URL = "https://reqres.in/api/";
    private static RetrofitClient mInstance;


    private RetrofitClient() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Log.i("CLIENT","BUILT RETRO CLIENT");
        }
    }

    public static synchronized RetrofitClient getInstance(){
        if(mInstance==null)
        {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public API getApi()
    {
        return retrofit.create(API.class);
    }
}
