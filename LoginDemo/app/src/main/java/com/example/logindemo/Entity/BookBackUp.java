package com.example.logindemo.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class BookBackUp implements Parcelable{
    Integer id;
    String bookName;
    String cover;
    String description;
    Integer quantity;
    String isbn;
    Integer status;
    public List<BorrowRequest> borrowRequests;
    public List<Cart> carts;

    public BookBackUp(Integer id, String bookName, String cover, String description, Integer quantity, String isbn, Integer status, List<BorrowRequest> borrowRequests, List<Cart> carts) {
        this.id = id;
        this.bookName = bookName;
        this.cover = cover;
        this.description = description;
        this.quantity = quantity;
        this.isbn = isbn;
        this.status = status;
        this.borrowRequests = borrowRequests;
        this.carts = carts;
    }

    public BookBackUp() {
    }

    protected BookBackUp(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        bookName = in.readString();
        cover = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            quantity = null;
        } else {
            quantity = in.readInt();
        }
        isbn = in.readString();
        if (in.readByte() == 0) {
            status = null;
        } else {
            status = in.readInt();
        }
        carts = in.createTypedArrayList(Cart.CREATOR);
    }

    public static final Creator<BookBackUp> CREATOR = new Creator<BookBackUp>() {
        @Override
        public BookBackUp createFromParcel(Parcel in) {
            return new BookBackUp(in);
        }

        @Override
        public BookBackUp[] newArray(int size) {
            return new BookBackUp[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<BorrowRequest> getBorrowRequests() {
        return borrowRequests;
    }

    public void setBorrowRequests(List<BorrowRequest> borrowRequests) {
        this.borrowRequests = borrowRequests;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
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
        dest.writeString(bookName);
        dest.writeString(cover);
        dest.writeString(description);
        if (quantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(quantity);
        }
        dest.writeString(isbn);
        if (status == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(status);
        }
        dest.writeTypedList(carts);
    }
}
