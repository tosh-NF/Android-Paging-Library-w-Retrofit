package com.cbwit.genius_challenge.Features.Model.POJO;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIResponse implements Parcelable {

    @SerializedName("page")
    private Integer page;

    @SerializedName("per_page")
    private Integer per_page;

    @SerializedName("total")
    private Integer total;

    @SerializedName("total_pages")
    private Integer total_pages;

    @SerializedName("data")
    private List<User> users;

    public APIResponse(Integer page, Integer per_page, Integer total, Integer total_pages, List<User> data) {
        this.page = page;
        this.per_page = per_page;
        this.total = total;
        this.total_pages = total_pages;
        this.users = data;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPer_page() {
        return per_page;
    }

    public void setPer_page(Integer per_page) {
        this.per_page = per_page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public static Creator<APIResponse> getCREATOR() {
        return CREATOR;
    }

    protected APIResponse(Parcel in) {
        if (in.readByte() == 0) {
            page = null;
        } else {
            page = in.readInt();
        }
        if (in.readByte() == 0) {
            per_page = null;
        } else {
            per_page = in.readInt();
        }
        if (in.readByte() == 0) {
            total = null;
        } else {
            total = in.readInt();
        }
        if (in.readByte() == 0) {
            total_pages = null;
        } else {
            total_pages = in.readInt();
        }
        users = in.createTypedArrayList(User.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (page == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(page);
        }
        if (per_page == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(per_page);
        }
        if (total == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(total);
        }
        if (total_pages == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(total_pages);
        }
        dest.writeTypedList(users);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<APIResponse> CREATOR = new Creator<APIResponse>() {
        @Override
        public APIResponse createFromParcel(Parcel in) {
            return new APIResponse(in);
        }

        @Override
        public APIResponse[] newArray(int size) {
            return new APIResponse[size];
        }
    };
}
