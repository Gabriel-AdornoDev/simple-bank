package org.gabriel.simplebank;


import java.util.ArrayList;
import java.util.List;

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

    //Debit method
    protected void debit(double amount) {
        balance -= amount;
    }

    //Withdraw method
    public String withdraw(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0.");
        }
        else if (amount > balance) {
            throw new InsufficientFundsException("Insufficient balance for this amount.");
        }
        else {
            debit(amount);
            return String.format("%.2f withdrawn successfully.\nCurrent balance: %.2f", amount, getBalance());
        }
    }

    //Transfer money method
    public String transfer(BankAccount conta, double amount) {
        try {
            this.withdraw(amount);
            conta.setBalance(conta.getBalance() + amount);
            return "Success";
        }
        catch (InsufficientFundsException e) {
            return "Error: "+ e.getMessage();
        }
        catch (InvalidAmountException e) {
            return "Error: "+ e.getMessage();
        }
    }

    //Check balance method
    public String checkBalance() {
        return String.format("Balance: %.2f", balance);
    }

    //Protected setBalance for security
    protected void setBalance(double amount) {
        balance = amount;
    }
}
