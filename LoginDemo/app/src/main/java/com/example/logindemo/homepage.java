package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.logindemo.APIobject.APIbook;
import com.example.logindemo.Adapter.BookAdapter;
import com.example.logindemo.Entity.Book;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class homepage extends AppCompatActivity implements BookAdapter.OnBookListener {
    private static final String TAG = homepage.class.getSimpleName();
    RecyclerView recyclerView, recyclerView1;
    ArrayList<Book> books;
    BookAdapter bookAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_homepage);
        recyclerView=findViewById(R.id.recy_id);
        recyclerView1=findViewById(R.id.recy_category);
        Call<List<Book>> call = APIbook.bookinterface().getAllBooks();
         call.enqueue(new Callback<List<Book>>() {
             @Override
             public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                 if(response.isSuccessful()){
                     final List<Book> bookList = response.body();
                     bookAdapter = new BookAdapter((ArrayList<Book>) bookList, homepage.this, new BookAdapter.OnBookListener() {
                         @Override
                         public void onBookClick(int position) {
                             Intent i = new Intent(homepage.this, DetailBook.class);
                             assert bookList != null;
                             i.putExtra("book_item", bookList.get(position));
                             startActivity(i);
                         }
                     });
                     Log.d(homepage.class.getSimpleName(), "onResponse: response body");
                     recyclerView.setAdapter(bookAdapter);
                 }else {
                     Toast.makeText(homepage.this, "An Error Occured", Toast.LENGTH_SHORT).show();
                 }
             }

             @Override
             public void onFailure(Call<List<Book>> call, Throwable t) {
                 Log.d(TAG,">>> onFailure "+ t.fillInStackTrace());
                 Toast.makeText(homepage.this, "Failed", Toast.LENGTH_SHORT).show();
             }
         });

        Call<List<Book>> call1 = APIbook.bookinterface().getAllBooks();
        call1.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                if(response.isSuccessful()){
                    final List<Book> bookList = response.body();
                    bookAdapter = new BookAdapter((ArrayList<Book>) bookList, homepage.this, new BookAdapter.OnBookListener() {
                        @Override
                        public void onBookClick(int position) {
                            Intent i = new Intent(homepage.this, DetailBook.class);
                            assert bookList != null;
                            i.putExtra("book_item", bookList.get(position));
                            startActivity(i);
                        }
                    });
                    Log.d(homepage.class.getSimpleName(), "onResponse: response body");
                    recyclerView1.setAdapter(bookAdapter);
                }else {
                    Toast.makeText(homepage.this, "An Error Occured", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.d(TAG,">>> onFailure "+ t.fillInStackTrace());
                Toast.makeText(homepage.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });
        LinearLayoutManager layoutManager =new LinearLayoutManager(homepage.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager((layoutManager));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager layoutManager1 =new LinearLayoutManager(homepage.this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager((layoutManager1));
        recyclerView1.setItemAnimator(new DefaultItemAnimator());
         //initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);

        //set home select
        bottomNavigationView.setSelectedItemId(R.id.homepage);

        // perform itemSelectedListerner
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
                       return true;
                   case R.id.profileActivity:
                       startActivity(new Intent(getApplicationContext(),ProfileActivity.class));
                       overridePendingTransition(0,0);
                       return true;

               }
                return false;
            }
        });
    }

    @Override
    public void onBookClick(int position) {
        books.get(position);
        Intent intent=new Intent(this,DetailBook.class);
        intent.putExtra("selected_note",books.get(position));
        startActivity(intent);
    }
}