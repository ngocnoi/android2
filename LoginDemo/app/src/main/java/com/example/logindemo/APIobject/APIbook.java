package com.example.logindemo.APIobject;

import com.example.logindemo.interfaceAPI.Bookinterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIbook {
    //define base url
    public static String base_url="http://192.168.1.12:8080";

    //
    public static Retrofit getBook(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
    public static Bookinterface bookinterface(){
        return getBook().create(Bookinterface.class);
    }
}
