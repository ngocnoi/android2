package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.logindemo.APIobject.APIbook;
import com.example.logindemo.Entity.Book;
import com.example.logindemo.Entity.User;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private static final String TAG = "MainActivity";
    SignInButton signInButton;
    private GoogleApiClient googleApiClient;
    private static final int SIGN_IN =1;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        googleApiClient = new GoogleApiClient.Builder(this).enableAutoManage(
                this, this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
        signInButton = findViewById(R.id.google_sign_in);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(intent, SIGN_IN);
            }
        });
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(final int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            final GoogleSignInAccount account = result.getSignInAccount();
            final String mail = account.getEmail();
            final String mailFullName=account.getDisplayName();
            String[] split = mail.split("@");
            String domain = split[1]; //This Will Give You The Domain After '@'
            final String []mailU = account.getEmail().split("@");
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

            if(domain.equals("fpt.edu.vn")) {
                if(result.isSuccess()){
                    Log.d(TAG, mailU[0]);
                    Log.d(TAG, mailFullName);
                    Call<User> callExist = APIbook.bookinterface().existUser(mailU[0]);
                    callExist.enqueue(new Callback<User>() {
                       @Override
                       public void onResponse(Call<User> call, Response<User> response) {
                           if (response.code() == 200) {
                               SharedPreferences.Editor editor = sharedpreferences.edit();
                               editor.putString("cardNumber",mailU[0]);
                               editor.commit();
                               Log.d(TAG, "onResponse: success, code 200");
                           } else if(response.code() == 404 ){
                               final User user=new User();
                                 user.setStatus(true);
                                 user.setPhone("");
                                 user.setMail(mail);
                                 user.setFullName(mailFullName);
                                 user.setCardNumber(mailU[0]);
                                 user.setAddress("");
                               SharedPreferences.Editor editor = sharedpreferences.edit();
                               editor.putString("cardNumber",mailU[0]);
                               editor.commit();
                              Call<User> createUser2=APIbook.bookinterface().createUser(user);
                               createUser2.enqueue(new Callback<User>() {
                                   @Override
                                   public void onResponse(Call<User> call, Response<User> response) {
                                       Toast.makeText(getApplicationContext(),"successfull",Toast.LENGTH_LONG).show();
                                   }

                                   @Override
                                   public void onFailure(Call<User> call, Throwable t) {
                                       Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                                   }
                               });
                           }else {
                               Log.d(TAG, "Something went wrong :( | Response code 2: " + response.code());
                           }
                       }

                       @Override
                       public void onFailure(Call<User> call, Throwable t) {
                           Log.d(TAG, "onFailure: "+t.fillInStackTrace());
                       }
                   });
//                    if(callExist.equals("")){
//
//                        Log.d(TAG, "onActivityResult: "+callExist.toString());
//                    }
                    startActivity(new Intent(MainActivity.this, homepage.class));
                    finish();
                }
            }else{
                Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                    }
                });
                Toast.makeText(this,"Login Fail", Toast.LENGTH_SHORT).show();
            }

        }
    }

}