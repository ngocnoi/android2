package com.example.logindemo.Entity;

import java.util.List;

public class BookOffer {
  Integer bookOfferId;
    String bookName;
    String bookImage;
    String description;
    public List<User> users;
    boolean status;

    public BookOffer(Integer bookOfferId, String bookName, String bookImage, String description, List<User> users, boolean status) {
        this.bookOfferId = bookOfferId;
        this.bookName = bookName;
        this.bookImage = bookImage;
        this.description = description;
        this.users = users;
        this.status = status;
    }

    public BookOffer() {
    }

    public Integer getBookOfferId() {
        return bookOfferId;
    }

    public void setBookOfferId(Integer bookOfferId) {
        this.bookOfferId = bookOfferId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
