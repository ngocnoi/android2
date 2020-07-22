package com.example.logindemo.Entity;

import java.util.List;

public class BorrowRequest {
    Integer requestId;
    String dateBorrow;
    String dateReturn;
    float penaltyMoney;
    Integer isDelete;
    Integer status;
    public List<User> users;
    public List<Librian> librians;
    public List<Book> books;

    public BorrowRequest(Integer requestId, String dateBorrow, String dateReturn, float penaltyMoney, Integer isDelete, Integer status, List<User> users, List<Librian> librians, List<Book> books) {
        this.requestId = requestId;
        this.dateBorrow = dateBorrow;
        this.dateReturn = dateReturn;
        this.penaltyMoney = penaltyMoney;
        this.isDelete = isDelete;
        this.status = status;
        this.users = users;
        this.librians = librians;
        this.books = books;
    }

    public BorrowRequest() {
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getDateBorrow() {
        return dateBorrow;
    }

    public void setDateBorrow(String dateBorrow) {
        this.dateBorrow = dateBorrow;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

    public float getPenaltyMoney() {
        return penaltyMoney;
    }

    public void setPenaltyMoney(float penaltyMoney) {
        this.penaltyMoney = penaltyMoney;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
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

    public List<Librian> getLibrians() {
        return librians;
    }

    public void setLibrians(List<Librian> librians) {
        this.librians = librians;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
