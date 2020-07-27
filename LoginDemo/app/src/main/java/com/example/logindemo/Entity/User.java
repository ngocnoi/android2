package com.example.logindemo.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class User implements Parcelable {
    Integer userId;
    String fullName;
    String address;
    String phone;
    String mail;
    String cardNumber;
    Boolean status;
    public List<Cart> carts;

    public User(Integer userId, String fullName, String address, String phone, String mail, String cardNumber, Boolean status, List<Cart> carts) {
        this.userId = userId;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
        this.cardNumber = cardNumber;
        this.status = status;
        this.carts = carts;
    }

    public User() {
    }

    protected User(Parcel in) {
        carts = new ArrayList<Cart>();
        in.readList(carts, getClass().getClassLoader());
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        fullName = in.readString();
        address = in.readString();
        phone = in.readString();
        mail = in.readString();
        cardNumber = in.readString();
        byte tmpStatus = in.readByte();
        status = tmpStatus == 0 ? null : tmpStatus == 1;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Cart> getCart() {
        return carts;
    }

    public void setCart(List<Cart> cart) {
        this.carts = cart;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(carts);
        if (userId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userId);
        }
        dest.writeString(fullName);
        dest.writeString(address);
        dest.writeString(phone);
        dest.writeString(mail);
        dest.writeString(cardNumber);
        dest.writeByte((byte) (status == null ? 0 : status ? 1 : 2));
    }
}
