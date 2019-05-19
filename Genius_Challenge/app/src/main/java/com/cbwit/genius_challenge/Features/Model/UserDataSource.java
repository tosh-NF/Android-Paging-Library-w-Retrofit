package com.cbwit.genius_challenge.Features.Model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.cbwit.genius_challenge.Features.Model.POJO.APIResponse;
import com.cbwit.genius_challenge.Features.Model.POJO.User;
import com.cbwit.genius_challenge.Features.Network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDataSource extends PageKeyedDataSource<Integer, User> {

    public static final int PAGE = 1;
    public static final int PAGE_SIZE = 3;






    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, User> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getResponse(PAGE)
                .enqueue(new Callback<APIResponse>() {
                    @Override
                    public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {

                        if(response.body() != null)
                        {

                            callback.onResult(response.body().getUsers(),null,PAGE +1);
                        }
                    }

                    @Override
                    public void onFailure(Call<APIResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, User> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getResponse(params.key)
                .enqueue(new Callback<APIResponse>() {
                    @Override
                    public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {

                        //check for a previous page
                        Integer key = (params.key >1) ? params.key -1 : null ;
                        if(key!=null)
                            Log.i("load_before_key",key.toString());
                        {
                            if(response.body() != null)
                            {
                                callback.onResult((response.body().getUsers()), key);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<APIResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, User> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getResponse(params.key)
                .enqueue(new Callback<APIResponse>() {
                    @Override
                    public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {

                        Integer key =
                                (response.body().getTotal_pages()*response.body().getPer_page() - response.body().getPage()*response.body().getPer_page() >= 1)
                                ? params.key+1 :null;
                        if(key!=null)
                            Log.i("load_before_key",key.toString());
                        if(response.body() != null)
                        {

                            callback.onResult((response.body().getUsers()), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<APIResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void invalidate() {
        super.invalidate();

    }



}
