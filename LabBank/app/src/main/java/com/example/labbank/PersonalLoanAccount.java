package com.example.labbank;

public class PersonalLoanAccount extends BankAccount {

    private static double interestRateChanges = 10.0; //The percentage of interest monthly or annualy.
    private static double loanAmount;
    //private final double loanAmount = getAmount()*(interestRateChanges+100)/100; //the amount to refund

    public PersonalLoanAccount(int id, String accountName, double amount, String iban, String currency) {
        super(id, accountName, amount, iban, currency);
        //double loanAmount = getAmount() * (100+interestRateChanges) / 100;
        //double loan = loanAmount;
    }

    public static double getInterestRateChanges() { return interestRateChanges; }
    public static void setInterestRateChanges(double interestRateChanges) { PersonalLoanAccount.interestRateChanges = interestRateChanges; }
    public static void setLoanAmount(double loanAmount) { PersonalLoanAccount.loanAmount = loanAmount; }
    public static double getLoanAmount() { return loanAmount; }

    @Override
    public String withdraw(double amount) {
        return null;
    }
    @Override
    public String transfer(double amount) { return null; }
    @Override
    public String purchase(double amount) { return null; }

    public static String payBack(double amount){
        if (amount <= getLoanAmount()){
            setLoanAmount(getLoanAmount() - amount);
            //setAmount(getAmount() - amount);
            return "You have paid back the amount of : " + amount;
        }
        return "You can't pay back more than the loan.";
    }
}