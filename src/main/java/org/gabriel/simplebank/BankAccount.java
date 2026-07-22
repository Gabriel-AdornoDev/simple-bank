package org.gabriel.simplebank;

//A Bank Account super class
public class BankAccount {
    private String accountHolder;
    private int accountNumber;
    private double balance = 0;

    //Constructor method
    public BankAccount(String accountHolder, int accountNumber) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
    }

    //Gets for accountHolder, accountNumber and balance
    public double getBalance() {
        return balance;
    }
    public String getAccountHolder() {
        return accountHolder;
    }
    public int getAccountNumber() {
        return accountNumber;
    }

    //Deposit method
    public String deposit(double amount) {
        if (amount > 0) {
            double previousBalance = balance;
            balance += amount;
            return String.format("Previous balance: %.2f\nCurrent Balance:%.2f", previousBalance, balance);
        } else {
            return String.format("%.2f is an invalid amount, try again.", amount);
        }
    }

    //Withdraw method
    public String withdraw(double amount) {
        if (amount <= 0) {
        return String.format("%.2f is an invalid amount.", amount);
        }
        else if (amount > balance) {
            return String.format("%.2f is more than your balance.", amount);
        }
        else {
            double previousBalance = balance;
            balance -= amount;
            return String.format("Previous balance: %.2f\nCurrent balance: %.2f", previousBalance, balance);
        }
    }

    //Check balance method
    public String checkBalance() {
        return String.format("Balance: %.2f", balance);
    }
    //Debit method
    protected void debit(double amount) {
        balance -= amount;
    }
    //Protected setBalance for security
    protected void setBalance(double amount) {
        balance += amount;
    }
}
