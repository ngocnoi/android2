package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.logindemo.Entity.Book;
import com.example.logindemo.Entity.Notification;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

public class NotificationDetail extends AppCompatActivity {
    private static final String TAG = "NotificationDetail";
    TextView textView23, textView24;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_notification_detail);
        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        Notification notification = (Notification) bundle.get("notification_item");
        textView23=findViewById(R.id.textView23);
        textView24=findViewById(R.id.textView24);
        if(notification != null) {

            textView23.setText(notification.getLibrian().getUsername());
            textView24.setText(notification.getDescription());
            Log.d(TAG, "onCreate: "+ new Gson().toJson(notification));
        }

        //initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        BottomNavigationView bottomNavigationView2=findViewById(R.id.top_navigation);

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
                    case R.id.profileActivity:
                        startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
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