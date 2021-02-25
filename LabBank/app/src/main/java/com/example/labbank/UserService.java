package com.example.labbank;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    //To check the user when logging
    @GET("config/")
    Call<List<LoginResponse>> checkUser();

    //To get the different accounts
    @GET("accounts")
    Call<List<BankAccountResponse>> getAccounts();

    //To add an account
    @POST("accounts/")
    Call<BankAccountResponse> addAccount(@Body BankAccountRequest bankAccountRequest);

    //To update an account (when withdrawal, deposit, ...)
    @PUT("accounts/{id}")
    Call<BankAccountResponse> putAccount(@Path("id") int id, @Body BankAccountRequest bankAccountRequest);
}
