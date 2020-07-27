package com.example.logindemo.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Cart implements Parcelable {
    Integer cardId;
    Integer status;
    public User user;
    public List<Book> books;
    public List<BookBackUp> bookBackUps;


    public Cart(Integer cardId, Integer status, User user, List<Book> books, List<BookBackUp> bookBackUps) {
        this.cardId = cardId;
        this.status = status;
        this.user = user;
        this.books = books;
        this.bookBackUps = bookBackUps;
    }

    public Cart() {
    }

    protected Cart(Parcel in) {
        user = (User) in.readParcelable(User.class.getClassLoader());
        bookBackUps = new ArrayList<BookBackUp>();
        in.readList(bookBackUps, getClass().getClassLoader());
        if (in.readByte() == 0) {
            cardId = null;
        } else {
            cardId = in.readInt();
        }
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        books = in.createTypedArrayList(Book.CREATOR);
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User users) {
        this.user = users;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<BookBackUp> getBookBackUps() {
        return bookBackUps;
    }

    public void setBookBackUps(List<BookBackUp> bookBackUps) {
        this.bookBackUps = bookBackUps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(user, flags);
        dest.writeList(bookBackUps);
        if (cardId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(cardId);
        }
        if (status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(status);
        }
        dest.writeTypedList(books);
    }
}
