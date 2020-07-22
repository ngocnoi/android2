package com.example.logindemo.Entity;

import java.util.List;

public class Cart {
    Integer cardId;
    Integer status;
    public List<User> users;
    public List<Book> books;

    public Cart(Integer cardId, Integer status, List<User> users, List<Book> books) {
        this.cardId = cardId;
        this.status = status;
        this.users = users;
        this.books = books;
    }

    public Cart() {
    }

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
