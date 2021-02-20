package com.example.labbank;

public class SavingsAccount extends BankAccount {

    private static double feesSaving = 5.0;
    private static double accountLimitLower = 100.0;

    public SavingsAccount(int id, String accountName, double amount, String iban, String currency) {
        super(id, accountName, amount, iban, currency);
    }

    public static double getAccountLimit() { return accountLimitLower; }
    public static void setAccountLimit(double accountLimitLower) { SavingsAccount.accountLimitLower = accountLimitLower; }
    public static double getFeesSaving() { return feesSaving; }
    public static void setFeesSaving(double feesSaving) { SavingsAccount.feesSaving = feesSaving; }

    @Override
    public String purchase(double amount) { return null; }
    @Override
    public String withdraw(double amount) {
        if (getAmount() - amount > accountLimitLower) {
            setAmount(getAmount() - amount);
            return "You have been debited with the amount of : " + amount;
        }
        return "You will exceed your lower limit.";
    }
    @Override
    public String transfer(double amount) { return null; }

}
