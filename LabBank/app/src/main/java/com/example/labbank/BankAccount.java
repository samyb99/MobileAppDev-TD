package com.example.labbank;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public abstract class BankAccount implements Serializable {
    private int id;
    private String accountName;
    private double amount;
    private String iban;
    private String currency;

    public BankAccount(int id, String accountName, double amount, String iban, String currency)
    {
        this.id = id;
        this.accountName = accountName;
        this.amount = amount;
        this.iban = iban;
        this.currency = currency;
    }
    public abstract String purchase(double amount);
    public abstract String withdraw(double amount);

    public String deposit(double amount){
        setAmount(getAmount() + amount);
        return "You have been credited with the amount of : " + amount;
    }

    ///////////////////////
    //GETTERS AND SETTERS//
    ///////////////////////
    //GETTER AND SETTER FOR ID
    public int getId(){ return id; }
    public void setId(int id){ this.id = id; }
    //GETTER AND SETTER FOR ACCOUNT NAME
    public String getAccountName(){ return accountName; }
    public void setAccountName(){ this.accountName = accountName; }
    //GETTER AND SETTER FOR AMOUNT
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    //GETTER AND SETTER FOR IBAN
    public String getIban() { return iban; }
    public void setIban(String iban) { this.iban = iban; }
    //GETTER AND SETTER FOR CURRENCY
    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    @NonNull
    @Override
    public String toString() {
        return "Bank Account " + '\'' +
                "Account name = " + accountName + '\''  +
                ", Amount = " + amount + '\'' +
                ", IBAN = " + iban + '\'' +
                ", Currency = " + currency;
    }

    public abstract String transfer(double amount);
}
