package com.example.labbank;

public class CreditCardAccount extends BankAccount {

    private static double accountLimitP = 1000.0; //your upper limit that you can spend.
    private static double accountLimitW = 4000.0; //your upper limit that you can withdraw
    private static double interestCharges = 10.0; //If 0 transactions per month, the interest will be applied.

    public CreditCardAccount(int id, String accountName, double amount, String iban, String currency) {
        super(id, accountName, amount, iban, currency);
    }

    public static double getInterestCharges() { return interestCharges; }
    public static void setInterestCharges(double interestCharges) { CreditCardAccount.interestCharges = interestCharges; }

    public static double getAccountLimitP() { return accountLimitP; }
    public static void setAccountLimitP(double accountLimitP) { CreditCardAccount.accountLimitP = accountLimitP; }
    public static double getAccountLimitW() { return accountLimitW; }
    public static void setAccountLimitW(double accountLimitW) { CreditCardAccount.accountLimitW = accountLimitW; }

    @Override
    public String withdraw(double amount) {
        if (amount <= getAmount() && amount <= accountLimitW){
            setAmount(getAmount() - amount);
            return "You have been debited of : " + amount + " for a withdrawing.";
        }
        return "You will exceed your upper limit.";
    }
    @Override
    public String purchase(double amount){
        if (amount <= accountLimitP && amount <= getAmount()){
            setAmount(getAmount() - amount);
            return "You have been debited of : " + amount + " for a purchasing.";
        }
        return "You don't have enough money";
    }

    @Override
    public String transfer(double amount){
        if (amount <= getAmount()){
            setAmount(getAmount() - amount);
            return "You have been debited of : " + amount + "for a transfering to ";
        }
        return "You don't have enough money";
    }

}
