package com.example.logindemo.Entity;

import java.util.List;

public class Author {
    Integer id;
    String bookAuthorName;
    String description;
    boolean status;

    public Author(Integer id, String bookAuthorName, String description, boolean status) {
        this.id = id;
        this.bookAuthorName = bookAuthorName;
        this.description = description;
        this.status = status;
    }

    public Author() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookAuthorName() {
        return bookAuthorName;
    }

    public void setBookAuthorName(String bookAuthorName) {
        this.bookAuthorName = bookAuthorName;
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
}
