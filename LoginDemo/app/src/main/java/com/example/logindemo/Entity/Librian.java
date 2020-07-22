package com.example.logindemo.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Librian implements Parcelable {
    Integer librianId;
    String username;
    String address;
    String phone;
    String mail;
    Integer status;

    protected Librian(Parcel in) {
        if (in.readByte() == 0) {
            librianId = null;
        } else {
            librianId = in.readInt();
        }
        username = in.readString();
        address = in.readString();
        phone = in.readString();
        mail = in.readString();
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
    }

    public static final Creator<Librian> CREATOR = new Creator<Librian>() {
        @Override
        public Librian createFromParcel(Parcel in) {
            return new Librian(in);
        }

        @Override
        public Librian[] newArray(int size) {
            return new Librian[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLibrianId() {
        return librianId;
    }

    public void setLibrianId(Integer librianId) {
        this.librianId = librianId;
    }

    public Librian(Integer librianId, String username, String address, String phone, String mail, Integer status) {
        this.librianId = librianId;
        this.username = username;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
        this.status = status;
    }

    public Librian() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (librianId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(librianId);
        }
        dest.writeString(username);
        dest.writeString(address);
        dest.writeString(phone);
        dest.writeString(mail);
        if (status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(status);
        }
    }
}
