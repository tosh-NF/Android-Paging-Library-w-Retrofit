package com.cbwit.genius_challenge.Features.ViewModel;

import android.app.AlertDialog;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.cbwit.genius_challenge.Features.Model.POJO.NewUserResponse;
import com.cbwit.genius_challenge.Features.Model.POJO.User;
import com.cbwit.genius_challenge.Features.Model.UserDataSource;
import com.cbwit.genius_challenge.Features.Model.UserDataSourceFactory;
import com.cbwit.genius_challenge.Features.Network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends AndroidViewModel {

    private LiveData userPagedList;
    private LiveData<PageKeyedDataSource<Integer, User>> liveDataSource;
    private MutableLiveData<Response<NewUserResponse>> mResponse = new MutableLiveData<>();


    public UserViewModel(Application application) {
        super(application);
        init(newDataSource());
    }

    public void init(UserDataSourceFactory userDataSourceFactory) {

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(UserDataSource.PAGE_SIZE + 2)
                        .build();

        userPagedList = new LivePagedListBuilder((userDataSourceFactory), config).build();
    }

    public void addUsers(@NonNull NewUserResponse post) {

        Call<NewUserResponse> createUserCall =
                RetrofitClient.getInstance()
                        .getApi()
                        .createPost(post);

        createUserCall.enqueue(new Callback<NewUserResponse>() {
            @Override
            public void onResponse(Call<NewUserResponse> call, Response<NewUserResponse> response) {
               /*
               get the API response
                */
                mResponse.setValue(response);
            }

            @Override
            public void onFailure(Call<NewUserResponse> call, Throwable t) {

            }
        });
    }

    public UserDataSourceFactory newDataSource() {
        UserDataSourceFactory userDataSourceFactory = new UserDataSourceFactory();
        liveDataSource = userDataSourceFactory.getUserLiveDataSource();

        return userDataSourceFactory;
    }

   public void responseMessage(Response<NewUserResponse> response, AlertDialog.Builder alert2) {
        if (response != null) {
            if (response.code() == 201) {
                alert2.setTitle("Success");
                alert2.setMessage("Code:" + response.code() + "\n"
                        + "Name: " + response.body().getName() + "\n"
                        + "Job: " + response.body().getJob() + "\n"
                        + "ID: " + response.body().getId());
            } else {
                alert2.setTitle("Response");
                alert2.setMessage("Code:" + response.code() + "\n"
                        + "Message: " + response.errorBody() + "\n");
            }
        }
    }

    public MutableLiveData<Response<NewUserResponse>> getmResponse() {
        return mResponse;
    }

    public void setmResponse(MutableLiveData<Response<NewUserResponse>> mResponse) {
        this.mResponse = mResponse;
    }

    public LiveData<PagedList<User>> getUserPagedList() {
        return userPagedList;
    }

    public void setUserPagedList(LiveData<PagedList<User>> userPagedList) {
        this.userPagedList = userPagedList;
    }

    public LiveData<PageKeyedDataSource<Integer, User>> getLiveDataSource() {
        return liveDataSource;
    }

    public void setLiveDataSource(LiveData<PageKeyedDataSource<Integer, User>> liveDataSource) {
        this.liveDataSource = liveDataSource;
    }
}

