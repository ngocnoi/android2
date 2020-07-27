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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logindemo.APIobject.APIbook;
import com.example.logindemo.Entity.Book;
import com.example.logindemo.Entity.BookBackUp;
import com.example.logindemo.Entity.Cart;
import com.example.logindemo.Entity.User;
import com.example.logindemo.Helper.DownloadImageTask;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class DetailBook extends AppCompatActivity {
    private static final String TAG = "DetailBook";
    TextView textView1, textView2, textView3,textView4;
    ImageView imageView1;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_detail_book);

        Bundle bundle = getIntent().getExtras();
        assert bundle != null;
        final Book book = (Book) bundle.get("book_item");
//
        imageView1=findViewById(R.id.imageView);
        textView1=findViewById(R.id.textTile);
        textView2=findViewById(R.id.textAuthor);
        textView3=findViewById(R.id.txtDescription);
        textView4=findViewById(R.id.textDetail);
        submit=findViewById(R.id.txtborrow);
        if(book != null) {
            new DownloadImageTask(imageView1)
                    .execute(book.getCover());
              textView1.setText(book.getBookName());
              //textView2.setText(book.getAuthors().get(0).getDescription());
              textView3.setText(book.getDescription());
              textView4.setText("BookShelf: "+book.getBookShelf().getBookShelfName()+"\n"+"Language: "+book.getLanguage().getLanguage()+"\n"+"ISBN: "+book.getIsbn()
             +"\n" +"Publisher: "+ book.getPublisher().getPublisherName()+"\n"+" Quantity: "+book.getQuantity()
              );

//            Book book=getIntent().getParcelableExtra("selected_note");
            Log.d(TAG, "onCreate: "+ new Gson().toJson(book));
             submit.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                     final SharedPreferences.Editor editor = sharedpreferences.edit();
                     String cardNumber=sharedpreferences.getString("cardNumber",null);
                     Log.d(TAG, "onClick: null is "+ cardNumber);
                     Call<User> callID = APIbook.bookinterface().getAllUserByCardNumber(cardNumber);

                     callID.enqueue(new Callback<User>() {
                         @Override
                         public void onResponse(Call<User> call, Response<User> response) {
                             User userA=new User();
                             User aUser=response.body();
                             Log.d(TAG, "onResponse: id: "+response.body()+ " "+aUser.getUserId());
                             Cart newCart=new Cart();
                             userA.setUserId(aUser.getUserId());
                             BookBackUp bookBackUp=new BookBackUp();
                             List<BookBackUp> bookBackUp1=new ArrayList<BookBackUp>();
                             bookBackUp.setBookName(book.getBookName());
                             bookBackUp.setCover(book.getCover());
                             bookBackUp.setDescription(book.getDescription());
                             bookBackUp.setId(book.getId());
                             bookBackUp.setIsbn(book.getIsbn());
                             bookBackUp.setQuantity(book.getQuantity());
                             bookBackUp.setStatus(1);
                             bookBackUp1.add(bookBackUp);
                             newCart.setBookBackUps(bookBackUp1);
                             newCart.setCardId(1);
                             Call<Void>  callCart = APIbook.bookinterface().addCart(newCart);
                             callCart.enqueue(new Callback<Void>() {
                                 @Override
                                 public void onResponse(Call<Void> call, Response<Void> response) {

                                     Toast.makeText(DetailBook.this,"success", Toast.LENGTH_SHORT).show();
                                 }

                                 @Override
                                 public void onFailure(Call<Void> call, Throwable t) {
                                     Toast.makeText(DetailBook.this,"fail", Toast.LENGTH_SHORT).show();
                                 }
                             });
                         }

                         @Override
                         public void onFailure(Call<User> call, Throwable t) {
                             Log.d(TAG, "onResponse: k nhan dc  ");
                         }
                     });
                     //userA.setUserId(idUser);

                 }
             });

        }

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