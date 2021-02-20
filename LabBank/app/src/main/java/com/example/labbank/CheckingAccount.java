package com.example.labbank;

public class CheckingAccount extends BankAccount {

    private static double feesCheck = 20.0;
    private static double accountLimit = 10000.0;

    public CheckingAccount(int id, String accountName, double amount, String iban, String currency) {
        super(id, accountName, amount, iban, currency);
    }


    public static double getFeesCheck() { return feesCheck; }
    public static void setFeesCheck(double feesCheck) { CheckingAccount.feesCheck = feesCheck; }
    public static double getAccountLimit() { return accountLimit; }
    public static void setAccountLimit(double accountLimit) { CheckingAccount.accountLimit = accountLimit; }

    @Override
    public String purchase(double amount) { return null; }
    @Override
    public String withdraw(double amount) {
        if (amount <= accountLimit && amount <= getAmount()){
            setAmount(getAmount() - amount);
            return "You have been debited of : " + amount;
        }
        return "You will exceed your upper limit.";
    }
    @Override
    public String transfer(double amount) { return null; }

}
