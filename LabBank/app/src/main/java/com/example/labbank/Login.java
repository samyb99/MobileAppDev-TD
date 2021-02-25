package com.example.labbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    TextInputEditText name, lastname;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //We define the name, last name and so on to login.
        name = findViewById(R.id.edName);
        lastname = findViewById(R.id.edLastName);
        btnLogin = findViewById(R.id.btnLogin);
        //Button to login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(name.getText().toString()) || TextUtils.isEmpty(lastname.getText().toString())){
                    Toast.makeText(Login.this, "Name / Last name required", Toast.LENGTH_LONG).show();
                }
                else{
                    login(); //Procceed to login
                }
            }
        });
    }

    public void login(){
        //Set a login request with the name/last name
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setName(name.getText().toString());
        loginRequest.setLastname(lastname.getText().toString());

        //Call the API URL to check if the user exists.
        Call<List<LoginResponse>> loginResponseCall = ApiClient.getUserService().checkUser();
        loginResponseCall.enqueue(new Callback<List<LoginResponse>>() {
            @Override
            public void onResponse(Call<List<LoginResponse>> call, Response<List<LoginResponse>> response) {
                if (response.isSuccessful()){
                    List<LoginResponse> loginResponses = response.body();
                    SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
                    SharedPreferences.Editor prefsEditor = mPrefs.edit();
                    Gson gson = new Gson();
                    String json = gson.toJson(loginResponses);
                    prefsEditor.putString("Users", json);
                    prefsEditor.commit();
                    boolean successOrNo = false;
                    for (LoginResponse loginResponse1 : loginResponses){
                        if (loginResponse1.getName().equals(loginRequest.getName()) && loginResponse1.getLastname().equals(loginRequest.getLastname())){
                            Toast.makeText(Login.this, "Login successful.", Toast.LENGTH_LONG).show();
                            successOrNo = true;
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(Login.this, AccountDetails2.class).putExtra("User name", loginResponse1.getName()).putExtra("User last name", loginResponse1.getLastname()));
                                }
                            }, 700);
                            break; }
                    }
                    if (successOrNo == false) {
                        Toast.makeText(Login.this, "Login failed.", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Login failed.", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<LoginResponse>> call, Throwable t) {
                Toast.makeText(Login.this, "An error occured.", Toast.LENGTH_LONG).show();
            }
        });

    }
}