package com.example.logindemo.interfaceAPI;



import com.example.logindemo.Entity.Book;
import com.example.logindemo.Entity.Notification;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Bookinterface {
    @GET("/api/book/books")
    Call<List<Book>> getAllBooks();


    @GET("/api/notification/notifications")
    Call<List<Notification>> getAllNotifications();




}
