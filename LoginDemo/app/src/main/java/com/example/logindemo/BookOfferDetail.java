package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.logindemo.APIobject.APIbook;
import com.example.logindemo.Entity.Book;
import com.example.logindemo.Entity.BookOffer;
import com.example.logindemo.Entity.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookOfferDetail extends AppCompatActivity {
    private static final String TAG = "BookOfferDetail";
    EditText title, description,link;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_book_offer);

        title=findViewById(R.id.txtTitle);
        description=findViewById(R.id.txtDescription);
        link=findViewById(R.id.txtLink);
        submit=findViewById(R.id.txtbutton);

        submit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                String cardNumber=sharedpreferences.getString("cardNumber",null);
                Call<User> callID = APIbook.bookinterface().getAllUserByCardNumber(cardNumber);
                callID.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        User userA=response.body();
                        userA.getUserId();
                        Log.d(TAG, "onResponse: "+response.body());
                        BookOffer bookOffer =new BookOffer();
                        bookOffer.setBookName(title.getText().toString());
                        bookOffer.setDescription(description.getText().toString());
                        bookOffer.setBookImage(link.getText().toString());
                        bookOffer.setStatus(true);
                        bookOffer.setUser(userA);
                        Call<BookOffer> callBookOffer = APIbook.bookinterface().createBookOffer(bookOffer);
                        callBookOffer.enqueue(new Callback<BookOffer>() {
                            @Override
                            public void onResponse(Call<BookOffer> call, Response<BookOffer> response) {
                                Toast.makeText(BookOfferDetail.this, "success", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<BookOffer> call, Throwable t) {
                                Toast.makeText(BookOfferDetail.this, "fail", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });


            }
        });



        //initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        BottomNavigationView bottomNavigationView2=findViewById(R.id.top_navigation);
        //set home select
        bottomNavigationView.setSelectedItemId(R.id.bookOfferMain);

        // perform itemselectedListerner
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.bookOfferMain:
                        startActivity(new Intent(getApplicationContext(), BookOfferDetail.class));
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