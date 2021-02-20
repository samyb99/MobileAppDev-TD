package com.example.labbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBankAccount extends AppCompatActivity {
    private EditText ibanTxt, currencyTxt;
    private RadioGroup choiceType;
    private int accountType;
    private Button btnAddAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bank_account);
        ibanTxt = findViewById(R.id.ibanText);
        currencyTxt = findViewById(R.id.currencyText);
        choiceType = findViewById(R.id.choiceOfType);
        choiceType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                accountType = choiceType.indexOfChild(findViewById(checkedId));
            }
        });
        btnAddAcc = findViewById(R.id.buttonAddNewAccount);
        btnAddAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(ibanTxt.getText().toString()) || TextUtils.isEmpty(currencyTxt.getText().toString())){
                    Toast.makeText(AddBankAccount.this, "Iban/currency required", Toast.LENGTH_LONG).show();
                }
                else{
                    //Procceed to add the account
                    addNewAccount();
                    Intent intent = new Intent(AddBankAccount.this, AccountDetails2.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void addNewAccount(){
        BankAccountRequest bankAccountRequest = new BankAccountRequest();
        bankAccountRequest.setCurrency(currencyTxt.getText().toString());
        bankAccountRequest.setIban(ibanTxt.getText().toString());
        bankAccountRequest.setAmount(0);
        //Check the account type
        if (accountType ==0){
            bankAccountRequest.setAccountName("Saving Account");
        }
        else if (accountType == 1){
            bankAccountRequest.setAccountName("Checking Account");
        }
        else if (accountType == 2){
            bankAccountRequest.setAccountName("Credit Card Account");
        }
        else if (accountType == 3){
            bankAccountRequest.setAccountName("Personal Loan Account");
        }

        Call<BankAccountResponse> bankAccountResponseCall = ApiClient.getUserService().addAccount(bankAccountRequest); //userLogin(loginRequest);
        bankAccountResponseCall.enqueue(new Callback<BankAccountResponse>() {
            @Override
            public void onResponse(Call<BankAccountResponse> call, Response<BankAccountResponse> response) {
                if (response.isSuccessful()){
                    Toast.makeText(AddBankAccount.this, "Account succesfully added.", Toast.LENGTH_LONG).show();
                    BankAccountResponse bankAccountResponse = response.body();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(AddBankAccount.this, AccountDetails2.class)); //.putExtra("data", bankAccountResponse.getId()));
                        }
                    }, 700);
                }
                else{
                    Toast.makeText(AddBankAccount.this, "Not added.", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<BankAccountResponse> call, Throwable t) {
                Toast.makeText(AddBankAccount.this, "An error occured.", Toast.LENGTH_LONG).show();
            }
        });
    }

}