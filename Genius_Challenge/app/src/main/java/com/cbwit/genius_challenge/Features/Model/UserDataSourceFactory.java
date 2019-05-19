package com.cbwit.genius_challenge.Features.Model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.cbwit.genius_challenge.Features.Model.POJO.User;

public class UserDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, User>> userLiveDataSource
            = new MutableLiveData<>();


    @Override
    public DataSource create() {

       UserDataSource userDataDource = new UserDataSource();
       userLiveDataSource.postValue(userDataDource);
        return userDataDource;
    }

    public LiveData<PageKeyedDataSource<Integer, User>> getUserLiveDataSource() {
        return userLiveDataSource;
    }


public void invalidate()
{
    userLiveDataSource.getValue().invalidate();
}

}
