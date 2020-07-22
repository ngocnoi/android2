package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.logindemo.APIobject.APIbook;
import com.example.logindemo.Adapter.BookAdapter;
import com.example.logindemo.Adapter.NotificationAdapter;
import com.example.logindemo.Entity.Book;
import com.example.logindemo.Entity.Notification;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationAll extends AppCompatActivity {
    private static final String TAG = "Notification";
    RecyclerView recyclerView;
    ArrayList<Notification> notifications;
    NotificationAdapter notificationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        recyclerView=findViewById(R.id.recy_id);

        Call<List<Notification>> call = APIbook.bookinterface().getAllNotifications();
        call.enqueue(new Callback<List<Notification>>() {
                         @Override
                         public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {
                             if (response.isSuccessful()) {
                                 final List<Notification> notification =response.body();
                                 notificationAdapter = new NotificationAdapter((ArrayList<Notification>) notification, NotificationAll.this, new NotificationAdapter.OnNotificationListener() {
                                     @Override
                                     public void onNotificationClick(int position) {
                                         Intent i = new Intent(NotificationAll.this, NotificationDetail.class);
                                         assert notification != null;
                                         i.putExtra("notification_item", notification.get(position));
                                         startActivity(i);
                                     }
                                 });
                                 recyclerView.setAdapter(notificationAdapter);
                             } else {
                                 Toast.makeText(NotificationAll.this, "An Error Occured", Toast.LENGTH_SHORT).show();
                             }
                         }

                         @Override
                         public void onFailure(Call<List<Notification>> call, Throwable t) {
                             Log.d(TAG,">>> onFailure "+ t.fillInStackTrace());
                             Toast.makeText(NotificationAll.this, "Failed", Toast.LENGTH_SHORT).show();
                         }
                     });



        LinearLayoutManager layoutManager = new LinearLayoutManager(NotificationAll.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager((layoutManager));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        BottomNavigationView bottomNavigationView2=findViewById(R.id.top_navigation);
        //set home select
       // bottomNavigationView.setSelectedItemId(R.id.homepage);

        // perform itemselectedListerner
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.bookOfferMain:
                        startActivity(new Intent(getApplicationContext(),BookOffer.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(),MyHistory.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.searchBook:
                        startActivity(new Intent(getApplicationContext(),SearchBook.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.homepage:
                        startActivity(new Intent(getApplicationContext(),homepage.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        //
        bottomNavigationView2.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.backPrevious:
                        return true;
                    case R.id.history:
                        startActivity(new Intent(getApplicationContext(),MyHistory.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.notification:
                        startActivity(new Intent(getApplicationContext(), NotificationAll.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.myCart:
                        startActivity(new Intent(getApplicationContext(),MyCart.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }


}