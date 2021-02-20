package com.example.labbank;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountDetails2 extends AppCompatActivity {
    private TextView nameU, lastNameU, pageNumero, lastP, idAccount, typeAccount, amountAccount, ibanAccount, currency,  feesSaving, feesChecking, feesS, feesC, interestR, interestRate, interestC, interestCharge, loanM, loanMoney, lowerL, lowerLimit, upperL, upperLimit, upperLP, upperLimitP;
    private Button transferM, payBackM, purchaseSmtg, btnN, btnP;
    private UserService jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_details);
        nameU = findViewById(R.id.nameUser);
        lastNameU = findViewById(R.id.lastNameUser);
        pageNumero = findViewById(R.id.pageNum);
        lastP = findViewById(R.id.lastPage);
        idAccount = findViewById(R.id.row_AccountID);
        typeAccount = findViewById(R.id.row_AccountType);
        amountAccount = findViewById(R.id.row_AccountAmount);
        ibanAccount = findViewById(R.id.row_AccountIBAN);
        currency = findViewById(R.id.row_Currency);
        feesSaving = findViewById(R.id.row_FeesSaving);
        feesChecking = findViewById(R.id.row_FeesCheck);
        feesS = findViewById(R.id.row_FeesS);
        feesC = findViewById(R.id.row_FeesC);
        interestC = findViewById(R.id.row_interestC);
        interestCharge = findViewById(R.id.row_interestCharge);
        interestR = findViewById(R.id.row_interestR);
        interestRate = findViewById(R.id.row_interestRate);
        transferM = findViewById(R.id.btnTransfer);
        payBackM = findViewById(R.id.btnPayBack);
        purchaseSmtg = findViewById(R.id.btnPurchase);
        loanM = findViewById(R.id.row_loan);
        loanMoney = findViewById(R.id.row_loanToRefund);
        lowerL = findViewById(R.id.row_lowerL);
        lowerLimit = findViewById(R.id.row_lowerLimit);
        upperL = findViewById(R.id.row_upperL);
        upperLimit = findViewById(R.id.row_upperLimit);
        upperLP = findViewById(R.id.row_upperLP);
        upperLimitP = findViewById(R.id.row_upperLimitP);
        btnN = findViewById(R.id.btnNext);
        btnP = findViewById(R.id.btnPrevious);
        btnN = (Button)findViewById(R.id.btnNext);
        btnN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pageNumeroInt = Integer.parseInt(pageNumero.getText().toString());
                ApiClient apiClient = new ApiClient();
                jsonPlaceHolderApi = apiClient.getUserService();
                if (pageNumeroInt + 1 > Integer.valueOf(lastP.getText().toString())){
                    pageNumero.setText("0");
                    getAccounts(0);
                }else{
                    pageNumeroInt = pageNumeroInt + 1;
                    pageNumero.setText(String.valueOf(pageNumeroInt));
                    getAccounts(pageNumeroInt);
                }
            }});
        btnP = (Button)findViewById(R.id.btnPrevious);
        btnP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pageNumeroInt = Integer.parseInt(pageNumero.getText().toString());
                ApiClient apiClient = new ApiClient();
                jsonPlaceHolderApi = apiClient.getUserService();
                if (pageNumeroInt - 1 <0) {
                    pageNumero.setText(lastP.getText().toString());
                    getAccounts(Integer.parseInt(lastP.getText().toString()));
                }else{
                    pageNumeroInt = pageNumeroInt - 1;
                    pageNumero.setText(String.valueOf(pageNumeroInt));
                    getAccounts(pageNumeroInt);
                }
            }
        });
        getAccounts(0);

    }

    private void showInfo(BankAccountResponse bankAccountResponse){
        idAccount.setText(String.valueOf(bankAccountResponse.getId()));
        typeAccount.setText(bankAccountResponse.getAccountName());
        nameU.setText(getIntent().getStringExtra("User name"));
        lastNameU.setText(getIntent().getStringExtra("User last name"));
        if (bankAccountResponse.getAccountName().equals("Savings Account")){
            //VISIBLE TEXT
            feesSaving.setVisibility(View.VISIBLE);
            feesS.setVisibility(View.VISIBLE);
            lowerLimit.setVisibility(View.VISIBLE);
            lowerL.setVisibility(View.VISIBLE);
            feesSaving.setText(String.valueOf(SavingsAccount.getFeesSaving()));
            lowerLimit.setText(String.valueOf(SavingsAccount.getAccountLimit()));
            //VISIBLE BUTTON

            //GONE TEXT
            feesChecking.setVisibility(View.GONE);
            feesC.setVisibility(View.GONE);
            interestR.setVisibility(View.GONE);
            interestRate.setVisibility(View.GONE);
            interestC.setVisibility(View.GONE);
            interestCharge.setVisibility(View.GONE);
            loanM.setVisibility(View.GONE);
            loanMoney.setVisibility(View.GONE);
            upperL.setVisibility(View.GONE);
            upperLimit.setVisibility(View.GONE);
            upperLP.setVisibility(View.GONE);
            upperLimitP.setVisibility(View.GONE);
            //GONE BUTTON
            purchaseSmtg.setVisibility(View.GONE);
            transferM.setVisibility(View.GONE);
            payBackM.setVisibility(View.GONE);
        }
        else if (bankAccountResponse.getAccountName().equals("Checking Account")){
            //VISIBLE TEXT
            feesChecking.setVisibility(View.VISIBLE);
            feesC.setVisibility(View.VISIBLE);
            upperLimit.setVisibility(View.VISIBLE);
            upperL.setVisibility(View.VISIBLE);
            feesChecking.setText(String.valueOf(CheckingAccount.getFeesCheck()));
            upperLimit.setText(String.valueOf(CheckingAccount.getAccountLimit()));
            //VISIBLE BUTTON
            //GONE TEXT
            feesSaving.setVisibility(View.GONE);
            feesS.setVisibility(View.GONE);
            interestR.setVisibility(View.GONE);
            interestRate.setVisibility(View.GONE);
            interestC.setVisibility(View.GONE);
            interestCharge.setVisibility(View.GONE);
            loanM.setVisibility(View.GONE);
            loanMoney.setVisibility(View.GONE);
            lowerL.setVisibility(View.GONE);
            lowerLimit.setVisibility(View.GONE);
            upperLP.setVisibility(View.GONE);
            upperLimitP.setVisibility(View.GONE);
            //GONE BUTTON
            purchaseSmtg.setVisibility(View.GONE);
            transferM.setVisibility(View.GONE);
            payBackM.setVisibility(View.GONE);
        }
        else if (bankAccountResponse.getAccountName().equals("Credit Card Account"))
        {
            //VISIBLE TEXT
            interestCharge.setVisibility(View.VISIBLE);
            interestC.setVisibility(View.VISIBLE);
            upperL.setVisibility(View.VISIBLE);
            upperLimit.setVisibility(View.VISIBLE);
            upperLP.setVisibility(View.VISIBLE);
            upperLimitP.setVisibility(View.VISIBLE);
            upperLimit.setText(String.valueOf(CreditCardAccount.getAccountLimitW()));
            upperLimitP.setText(String.valueOf(CreditCardAccount.getAccountLimitP()));
            interestCharge.setText(String.valueOf(CreditCardAccount.getInterestCharges()));
            //VISIBLE BUTTON
            transferM.setVisibility(View.VISIBLE);
            purchaseSmtg.setVisibility(View.VISIBLE);
            //GONE TEXT
            feesChecking.setVisibility(View.GONE);
            feesC.setVisibility(View.GONE);
            feesSaving.setVisibility(View.GONE);
            feesS.setVisibility(View.GONE);
            interestR.setVisibility(View.GONE);
            interestRate.setVisibility(View.GONE);
            loanM.setVisibility(View.GONE);
            loanMoney.setVisibility(View.GONE);
            lowerL.setVisibility(View.GONE);
            lowerLimit.setVisibility(View.GONE);
            //GONE BUTTON
            payBackM.setVisibility(View.GONE);
        }
        else if (bankAccountResponse.getAccountName().equals("Personal Loan Account"))
        {
            //VISIBLE TEXT
            interestR.setVisibility(View.VISIBLE);
            interestRate.setVisibility(View.VISIBLE);
            loanMoney.setVisibility(View.VISIBLE);
            loanM.setVisibility(View.VISIBLE);
            loanMoney.setText(String.valueOf(PersonalLoanAccount.getLoanAmount()));
            interestRate.setText(String.valueOf(PersonalLoanAccount.getInterestRateChanges()));
            //VISIBLE BUTTON
            payBackM.setVisibility(View.VISIBLE);
            //GONE TEXT
            feesChecking.setVisibility(View.GONE);
            feesC.setVisibility(View.GONE);
            feesSaving.setVisibility(View.GONE);
            feesS.setVisibility(View.GONE);
            interestC.setVisibility(View.GONE);
            interestCharge.setVisibility(View.GONE);
            transferM.setVisibility(View.GONE);
            lowerL.setVisibility(View.GONE);
            lowerLimit.setVisibility(View.GONE);
            upperL.setVisibility(View.GONE);
            upperLimit.setVisibility(View.GONE);
            //GONE BUTTON
            purchaseSmtg.setVisibility(View.GONE);
            transferM.setVisibility(View.GONE);
        }
        amountAccount.setText(String.valueOf(bankAccountResponse.getAmount()));
        ibanAccount.setText(bankAccountResponse.getIban());
        currency.setText(bankAccountResponse.getCurrency());
    }


    private void getAccounts(int numeroAcc) {
        int pageNumeroInt = Integer.parseInt(pageNumero.getText().toString());
        //OFFLINE GSON
        //SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        //Gson gson = new Gson();
        //String json = mPrefs.getString("Bank accounts", "");
        //List<BankAccountResponse> bankAccounts = (List<BankAccountResponse>) gson.fromJson(json, BankAccountResponse.class);
        //showInfo(bankAccounts.get(numeroAcc));

        //API CLIENT (WITH TLS)
        ApiClient apiClient = new ApiClient();
        jsonPlaceHolderApi = apiClient.getUserService();
        Call<List<BankAccountResponse>> call = jsonPlaceHolderApi.getAccounts();
        call.enqueue(new Callback<List<BankAccountResponse>>() {
            @Override
            public void onResponse(Call<List<BankAccountResponse>> call, Response<List<BankAccountResponse>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(AccountDetails2.this, "An error occured.", Toast.LENGTH_LONG).show();
                    return;
                }
                List<BankAccountResponse> bankAccounts = response.body();
                Integer countNumber = 0;
                for (BankAccountResponse bankAccountResponse : bankAccounts){
                    countNumber +=1;
                }
                countNumber -= 1;

                //SAVE USER'S DATA
                SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                Gson gson = new Gson();
                String json = gson.toJson(bankAccounts);
                prefsEditor.putString("Bank accounts", json);
                prefsEditor.commit();

                //SHOW THE ACCOUNT
                showInfo(bankAccounts.get(numeroAcc));
                lastP.setText(countNumber.toString());
            }

            @Override
            public void onFailure(Call<List<BankAccountResponse>> call, Throwable t) {
                Toast.makeText(AccountDetails2.this, "An error occured.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void addAccount(View view){
        Intent intent = new Intent(AccountDetails2.this, AddBankAccount.class);
        startActivity(intent);
    }

    public void depositMoney(View view) {
        int idAccount = Integer.parseInt(pageNumero.getText().toString())+1;
        String accountType = typeAccount.getText().toString();
        String ibanT = ibanAccount.getText().toString();
        String currencyT = currency.getText().toString();
        BankAccountRequest bankAccountRequest = new BankAccountRequest();
        bankAccountRequest.setAccountName(accountType);
        bankAccountRequest.setCurrency(currencyT);
        bankAccountRequest.setIban(ibanT);
        //SET AMOUNT
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Deposit");
        final EditText editText = new EditText(this);
        editText.setHint("Enter the amount to deposit : ");
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(editText);
        builder.setPositiveButton("Deposit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                double amount = Double.parseDouble(editText.getText().toString());
                //amountAccount.setText(String.valueOf(bankAccount.getAmount()));
                double totalAmount = amount + Double.parseDouble(amountAccount.getText().toString());
                bankAccountRequest.setAmount(totalAmount);
                if (amount >= 0){
                Call<BankAccountResponse> bankAccountResponseCall = ApiClient.getUserService().putAccount(idAccount, bankAccountRequest);
                bankAccountResponseCall.enqueue(new Callback<BankAccountResponse>() {
                    @Override
                    public void onResponse(Call<BankAccountResponse> call, Response<BankAccountResponse> response) {
                        if (response.isSuccessful()){
                            BankAccountResponse bankAccountResponse = response.body();
                            Toast.makeText(getApplicationContext(),"You have deposit the amount.",Toast.LENGTH_LONG).show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(AccountDetails2.this, AccountDetails2.class)); //.putExtra("data", bankAccountResponse.getId()));
                                }
                            }, 700);
                        }
                        else{
                            Toast.makeText(AccountDetails2.this, "Can't deposit.", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BankAccountResponse> call, Throwable t) {
                        Toast.makeText(AccountDetails2.this, "An error occured.", Toast.LENGTH_LONG).show();
                    }
                });
                } else{
                    amount = 0;
                    Toast.makeText(AccountDetails2.this, "An error occured.", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        //FINISH SETTING AMOUNT
    }



    public void withdrawMoney(View view){
        int idAccount = Integer.parseInt(pageNumero.getText().toString())+1;
        String accountType = typeAccount.getText().toString();
        String ibanT = ibanAccount.getText().toString();
        String currencyT = currency.getText().toString();
        BankAccountRequest bankAccountRequest = new BankAccountRequest();
        bankAccountRequest.setAccountName(accountType);
        bankAccountRequest.setCurrency(currencyT);
        bankAccountRequest.setIban(ibanT);
        //SET AMOUNT
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Withdraw");
        final EditText editText = new EditText(this);
        editText.setHint("Enter the amount to withdraw : ");
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(editText);
        builder.setPositiveButton("Withdraw", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                double amount = Double.parseDouble(editText.getText().toString());
                double totalAmount = Double.parseDouble(amountAccount.getText().toString()) - amount;
                bankAccountRequest.setAmount(totalAmount);
                if ((amount >= 0 && totalAmount >= Double.parseDouble(lowerLimit.getText().toString()) && accountType.equals("Savings Account")) ||
                        (Double.parseDouble(upperLimit.getText().toString()) >= amount && amount >= 0 && totalAmount >= 0 && accountType.equals("Checking Account")) ||
                        (Double.parseDouble(upperLimit.getText().toString()) >= amount && amount >= 0 && totalAmount >= 0 && accountType.equals("Credit Card Account")) ||
                        (amount >=0 && totalAmount >=0 && accountType.equals("Personal Loan Account"))){
                    Call<BankAccountResponse> bankAccountResponseCall = ApiClient.getUserService().putAccount(idAccount, bankAccountRequest);
                    bankAccountResponseCall.enqueue(new Callback<BankAccountResponse>() {
                        @Override
                        public void onResponse(Call<BankAccountResponse> call, Response<BankAccountResponse> response) {
                            if (response.isSuccessful()){
                                //Toast.makeText(AccountDetails2.this, "Account succesfully updated.", Toast.LENGTH_LONG).show();
                                BankAccountResponse bankAccountResponse = response.body();
                                Toast.makeText(getApplicationContext(),"You have withdraw." ,Toast.LENGTH_LONG).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(AccountDetails2.this, AccountDetails2.class));
                                    }
                                }, 700);
                            }
                            else{
                                Toast.makeText(AccountDetails2.this, "Can't withdraw. Check the amount/your upper limit.", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<BankAccountResponse> call, Throwable t) {
                            Toast.makeText(AccountDetails2.this, "An error occured.", Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    amount = 0;
                    Toast.makeText(AccountDetails2.this, "An error occured.", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void purchaseSmtg(View view){
        int idAccount = Integer.parseInt(pageNumero.getText().toString())+1;
        String accountType = typeAccount.getText().toString();
        String ibanT = ibanAccount.getText().toString();
        String currencyT = currency.getText().toString();
        BankAccountRequest bankAccountRequest = new BankAccountRequest();
        bankAccountRequest.setAccountName(accountType);
        bankAccountRequest.setCurrency(currencyT);
        bankAccountRequest.setIban(ibanT);
        //SET AMOUNT
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Purchasing");
        final EditText editText = new EditText(this);
        editText.setHint("Enter the amount for the purchasing : ");
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(editText);
        builder.setPositiveButton("Purchase", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                double amount = Double.parseDouble(editText.getText().toString());
                double totalAmount = Double.parseDouble(amountAccount.getText().toString()) - amount;
                bankAccountRequest.setAmount(totalAmount);
                if (Double.parseDouble(upperLimitP.getText().toString()) >= amount && amount >=0  && accountType.equals("Credit Card Account") && totalAmount >= 0){
                    Call<BankAccountResponse> bankAccountResponseCall = ApiClient.getUserService().putAccount(idAccount, bankAccountRequest);
                    bankAccountResponseCall.enqueue(new Callback<BankAccountResponse>() {
                        @Override
                        public void onResponse(Call<BankAccountResponse> call, Response<BankAccountResponse> response) {
                            if (response.isSuccessful()){
                                BankAccountResponse bankAccountResponse = response.body();
                                Toast.makeText(getApplicationContext(),"You have purchased something." ,Toast.LENGTH_LONG).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(AccountDetails2.this, AccountDetails2.class)); //.putExtra("data", bankAccountResponse.getId()));
                                    }
                                }, 700);
                            }
                            else{
                                Toast.makeText(AccountDetails2.this, "Can't purchase. Check the amount/your upper limit.", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<BankAccountResponse> call, Throwable t) {
                            Toast.makeText(AccountDetails2.this, "An error occured.", Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    amount = 0;
                    Toast.makeText(AccountDetails2.this, "An error occured.", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void payBackMoney(View view){
        int idAccount = Integer.parseInt(pageNumero.getText().toString())+1;
        String accountType = typeAccount.getText().toString();
        String ibanT = ibanAccount.getText().toString();
        String currencyT = currency.getText().toString();
        BankAccountRequest bankAccountRequest = new BankAccountRequest();
        bankAccountRequest.setAccountName(accountType);
        bankAccountRequest.setCurrency(currencyT);
        bankAccountRequest.setIban(ibanT);
        //SET AMOUNT
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Pay back");
        final EditText editText = new EditText(this);
        editText.setHint("Enter the amount to pay back : ");
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(editText);
        builder.setPositiveButton("Pay back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                double amount = Double.parseDouble(editText.getText().toString());
                double totalAmount = Double.parseDouble(amountAccount.getText().toString()) - amount;
                bankAccountRequest.setAmount(totalAmount);
                if (amount>=0 && totalAmount >=0 && accountType.equals("Personal Loan Account")){
                    Call<BankAccountResponse> bankAccountResponseCall = ApiClient.getUserService().putAccount(idAccount, bankAccountRequest);
                    bankAccountResponseCall.enqueue(new Callback<BankAccountResponse>() {
                        @Override
                        public void onResponse(Call<BankAccountResponse> call, Response<BankAccountResponse> response) {
                            if (response.isSuccessful()){
                                BankAccountResponse bankAccountResponse = response.body();
                                Toast.makeText(getApplicationContext(),"You have paidback." ,Toast.LENGTH_LONG).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(AccountDetails2.this, AccountDetails2.class));
                                    }
                                }, 700);
                            }
                            else{
                                Toast.makeText(AccountDetails2.this, "Can't pay back. Check the amount.", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<BankAccountResponse> call, Throwable t) {
                            Toast.makeText(AccountDetails2.this, "An error occured.", Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    amount = 0;
                    Toast.makeText(AccountDetails2.this, "An error occured.", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void transferMoney(View view) {
        int idAccount = Integer.parseInt(pageNumero.getText().toString())+1;
        String accountType = typeAccount.getText().toString();
        String ibanT = ibanAccount.getText().toString();
        String currencyT = currency.getText().toString();
        BankAccountRequest bankAccountRequest = new BankAccountRequest();
        bankAccountRequest.setAccountName(accountType);
        bankAccountRequest.setCurrency(currencyT);
        bankAccountRequest.setIban(ibanT);
        //SET AMOUNT
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Transfer of money");
        final EditText editText = new EditText(this);
        editText.setHint("Enter the amount to transfer : ");
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(editText);
        builder.setPositiveButton("Transfer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                double amount = Double.parseDouble(editText.getText().toString());
                double totalAmount = Double.parseDouble(amountAccount.getText().toString()) - amount;
                bankAccountRequest.setAmount(totalAmount);
                if (amount >= 0 && totalAmount >= 0 && accountType.equals("Credit Card Account")){
                    Call<BankAccountResponse> bankAccountResponseCall = ApiClient.getUserService().putAccount(idAccount, bankAccountRequest);
                    bankAccountResponseCall.enqueue(new Callback<BankAccountResponse>() {
                        @Override
                        public void onResponse(Call<BankAccountResponse> call, Response<BankAccountResponse> response) {
                            if (response.isSuccessful()){
                                BankAccountResponse bankAccountResponse = response.body();
                                Toast.makeText(getApplicationContext(),"You have transfered money." ,Toast.LENGTH_LONG).show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        startActivity(new Intent(AccountDetails2.this, AccountDetails2.class));
                                    }
                                }, 700);
                            }
                            else{
                                Toast.makeText(AccountDetails2.this, "Can't transfer money. Check the amount.", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<BankAccountResponse> call, Throwable t) {
                            Toast.makeText(AccountDetails2.this, "An error occured.", Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    amount = 0;
                    Toast.makeText(AccountDetails2.this, "An error occured.", Toast.LENGTH_LONG).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
