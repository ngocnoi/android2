package com.example.logindemo.Entity;

import java.util.List;

public class Image {
    Integer imageId;
    String imageBook;
    boolean status;
    public List<Book> books;

    public Image(Integer imageId, String imageBook, boolean status, List<Book> books) {
        this.imageId = imageId;
        this.imageBook = imageBook;
        this.status = status;
        this.books = books;
    }

    public Image() {
    }

    public Integer getImageId() {
        return imageId;
    }

    public void setImageId(Integer imageId) {
        this.imageId = imageId;
    }

    public String getImageBook() {
        return imageBook;
    }

    public void setImageBook(String imageBook) {
        this.imageBook = imageBook;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
