package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.logindemo.Entity.Book;
import com.example.logindemo.Helper.DownloadImageTask;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

public class DetailBook extends AppCompatActivity {
    private static final String TAG = "DetailBook";
    TextView textView1, textView2, textView3,textView4;
    ImageView imageView1;
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
        Book book = (Book) bundle.get("book_item");
//
        imageView1=findViewById(R.id.imageView);
        textView1=findViewById(R.id.textTile);
        textView2=findViewById(R.id.textAuthor);
        textView3=findViewById(R.id.txtDescription);
        textView4=findViewById(R.id.textDetail);
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