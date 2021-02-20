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

    //@POST("Users/")
    //Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);

    @GET("Users/")
    Call<List<LoginResponse>> checkUser();

    //@POST("Users/")
    //Call<LoginResponse> checkLogin(@Header("Authorization") String authToken);

    //@GET("accounts/{id}")
    //Call<BankAccountResponse> getAccount(@Path("id") int id);

    @GET("accounts")
    Call<List<BankAccountResponse>> getAccounts();

    @POST("accounts/")
    Call<BankAccountResponse> addAccount(@Body BankAccountRequest bankAccountRequest);

    @PUT("accounts/{id}")
    Call<BankAccountResponse> putAccount(@Path("id") int id, @Body BankAccountRequest bankAccountRequest);
}
