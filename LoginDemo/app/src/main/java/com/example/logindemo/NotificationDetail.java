package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.logindemo.Entity.Book;
import com.example.logindemo.Entity.Notification;
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
    }
}