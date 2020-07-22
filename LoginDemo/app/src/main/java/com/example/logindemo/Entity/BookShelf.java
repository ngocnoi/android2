package com.example.logindemo.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class BookShelf implements Parcelable {
    Integer id;
    String bookShelfName;
    String description;
    boolean status;

    public BookShelf(Integer id, String bookShelfName, String description, boolean status) {
        this.id = id;
        this.bookShelfName = bookShelfName;
        this.description = description;
        this.status = status;
    }

    public BookShelf() {
    }

    protected BookShelf(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        bookShelfName = in.readString();
        description = in.readString();
        status = in.readByte() != 0;
    }

    public static final Creator<BookShelf> CREATOR = new Creator<BookShelf>() {
        @Override
        public BookShelf createFromParcel(Parcel in) {
            return new BookShelf(in);
        }

        @Override
        public BookShelf[] newArray(int size) {
            return new BookShelf[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookShelfName() {
        return bookShelfName;
    }

    public void setBookShelfName(String bookShelfName) {
        this.bookShelfName = bookShelfName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(bookShelfName);
        dest.writeString(description);
        dest.writeByte((byte) (status ? 1 : 0));
    }
}
