package com.cbwit.genius_challenge.Features.Model.POJO;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewUserResponse implements Parcelable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("job")
    private String job;


    public NewUserResponse(String name, String job) {
        this.name = name;
        this.job = job;
    }

    protected NewUserResponse(Parcel in) {

        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        job = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(job);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<NewUserResponse> CREATOR = new Creator<NewUserResponse>() {
        @Override
        public NewUserResponse createFromParcel(Parcel in) {
            return new NewUserResponse(in);
        }

        @Override
        public NewUserResponse[] newArray(int size) {
            return new NewUserResponse[size];
        }
    };


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

}
