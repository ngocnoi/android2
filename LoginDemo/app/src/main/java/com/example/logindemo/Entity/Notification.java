package com.example.logindemo.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Notification implements Parcelable {
    Integer noId;
    String description;
    Boolean status;
    Librian librian;

    protected Notification(Parcel in) {
        librian = (Librian) in.readParcelable(Librian.class.getClassLoader());
        if (in.readByte() == 0) {
            noId = null;
        } else {
            noId = in.readInt();
        }
        description = in.readString();
        byte tmpStatus = in.readByte();
        status = tmpStatus == 0 ? null : tmpStatus == 1;
    }

    public static final Creator<Notification> CREATOR = new Creator<Notification>() {
        @Override
        public Notification createFromParcel(Parcel in) {
            return new Notification(in);
        }

        @Override
        public Notification[] newArray(int size) {
            return new Notification[size];
        }
    };

    public Integer getNoId() {
        return noId;
    }

    public void setNoId(Integer noId) {
        this.noId = noId;
    }





    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Librian getLibrian() {
        return librian;
    }

    public void setLibrian(Librian librian) {
        this.librian = librian;
    }

    public Notification() {
    }

    public Notification(Integer noId, String description, Boolean status, Librian librian) {
        this.noId = noId;
        this.description = description;
        this.status = status;
        this.librian = librian;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(librian, flags);
        if (noId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(noId);
        }
        dest.writeString(description);
        dest.writeByte((byte) (status == null ? 0 : status ? 1 : 2));
    }
}
