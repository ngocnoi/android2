package com.example.logindemo.interfaceAPI;



import com.example.logindemo.Entity.Book;
import com.example.logindemo.Entity.BookOffer;
import com.example.logindemo.Entity.Notification;
import com.example.logindemo.Entity.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Bookinterface {
    @GET("/api/book/books")
    Call<List<Book>> getAllBooks();

    @GET("/api/notification/notifications")
    Call<List<Notification>> getAllNotifications();

    @GET("/api/existUser/{cardNumber}")
     Call<User> existUser(@Path("cardNumber") String cardNumber);

    @POST("/api/addUser/new")
    Call<User> createUser(@Body User user);

    @PUT("/api/updateUser/{id}")
    Call<Void> updateUser(@Path("id") Integer id, @Body User user);

    @GET("/api/user/{cardNumber}")
    Call<User>  getAllUserByCardNumber(@Path("cardNumber") final String cardNumber);

    @POST("/api/addBookOffer/new")
    Call<BookOffer> createBookOffer(@Body BookOffer bookOffer);
}
