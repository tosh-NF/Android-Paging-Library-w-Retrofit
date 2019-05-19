package com.cbwit.genius_challenge.Features.Model.POJO;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import androidx.databinding.BaseObservable;
import com.google.gson.annotations.SerializedName;

public class User extends BaseObservable implements Parcelable {

    @SerializedName("first_name")
    private String first_name;
    @SerializedName("id")
    private Integer id;
    @SerializedName("email")
    private String email;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("last_name")
    private String last_name;

    public User(String first_name, Integer id, String email, String avatar, String last_name) {
        this.first_name = first_name;
        this.id = id;
        this.email = email;
        this.avatar = avatar;
        this.last_name = last_name;
        Log.i("USER","INSTANCE OF USER");
    }

    protected User(Parcel in) {
        first_name = in.readString();
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        email = in.readString();
        avatar = in.readString();
        last_name = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(first_name);
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(email);
        dest.writeString(avatar);
        dest.writeString(last_name);
    }
}
