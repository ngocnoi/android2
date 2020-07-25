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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.logindemo.APIobject.APIbook;
import com.example.logindemo.Entity.User;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "ProfileActivity";
    private ImageView profile_image;
    private TextView name, username, mail;
    private EditText phone, address;
    private Button signOutBtn;
    private Button submit;

    private GoogleApiClient googleApiClient;
    private GoogleSignInOptions gso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_profile);

        profile_image = findViewById(R.id.profile_image);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        mail = findViewById(R.id.mail);
        signOutBtn = findViewById(R.id.signOutBtn);
        phone=findViewById(R.id.txtPhone);
        address=findViewById(R.id.txtAddress);
        submit=findViewById(R.id.txtsubmit);

        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        String cardNumber=sharedpreferences.getString("cardNumber",null);
        Log.d(TAG, "cardNumber "+cardNumber);
        Call<User> callID = APIbook.bookinterface().getAllUserByCardNumber(cardNumber);
         callID.enqueue(new Callback<User>() {
             @Override
             public void onResponse(Call<User> call, Response<User> response) {
                 User aUser=response.body();
                address.setText(aUser.getAddress());
                 phone.setText(aUser.getPhone());
                 Log.d(TAG, "onResponse: "+response.body());
             }

             @Override
             public void onFailure(Call<User> call, Throwable t) {

             }
         });
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleApiClient =new GoogleApiClient.Builder(this).enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        if(status.isSuccess()){
                            SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.MyPREFERENCES, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.clear();
                            editor.commit();
                            gotoMainActivity();
                        }else{
                            Toast.makeText(ProfileActivity.this,"Log out Fail!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
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
                               User aUserB=response.body();
                               aUserB.getUserId();
                               User ab=new User();
                               ab.setPhone(phone.getText().toString());
                               ab.setAddress(address.getText().toString());
                        Call<Void> callUpdate = APIbook.bookinterface().updateUser(aUserB.getUserId(),ab);
                        callUpdate.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(Call<Void> call, Response<Void> response) {
                                Toast.makeText(ProfileActivity.this,"success", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<Void> call, Throwable t) {
                                Toast.makeText(ProfileActivity.this,"fail", Toast.LENGTH_SHORT).show();
                            }
                        });
                        }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            }
        } );

        //initialize and assign variable
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        //set home select
         bottomNavigationView.setSelectedItemId(R.id.profileActivity);

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

    }

    private void gotoMainActivity(){
        startActivity(new Intent( ProfileActivity.this, MainActivity.class));
        finish();
    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void handleSignInResult(GoogleSignInResult result){
        GoogleSignInAccount account = result.getSignInAccount();
        if (result.isSuccess()) {
            String []mailU = account.getEmail().split("@");
            name.setText(account.getDisplayName());
            username.setText(mailU[0]);
            mail.setText(account.getEmail());
            Picasso.get().load(account.getPhotoUrl()).placeholder(R.drawable.ic_background_photo).into(profile_image);
        }else {
            gotoMainActivity();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> opr =Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(opr.isDone()){
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        }else{
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult result) {
                    handleSignInResult(result);
                }
            });
        }
    }
}