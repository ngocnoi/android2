package com.example.logindemo.Entity;

import java.util.List;

public class BookOffer {
  Integer bookOfferId;
    String bookName;
    String bookImage;
    String description;
    public User user;
    Boolean status;

    public BookOffer(Integer bookOfferId, String bookName, String bookImage, String description, User user, Boolean status) {
        this.bookOfferId = bookOfferId;
        this.bookName = bookName;
        this.bookImage = bookImage;
        this.description = description;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
