package org.gabriel.simplebank;

//A Bank Account super class
public abstract class BankAccount {
    private String accountHolder;
    private final int accountNumber;
    private double balance = 0;
    private static long totalAccounts;

    //Constructor method
    public BankAccount(String accountHolder, int accountNumber) {
        if (accountHolder == null || accountHolder.isBlank()) {
            throw new InvalidAccountHolderException("Invalid holder name");
        }
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        totalAccounts++;
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
    public abstract String withdraw(double amount);

    //Transfer money method
    public String transfer(BankAccount account, double amount) {
        try {
            this.withdraw(amount);
            account.setBalance(account.getBalance() + amount);
            return "Success";
        }
        catch (InsufficientFundsException  | InvalidAmountException e) {
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

    //Get total accounts method
    public static String getTotalAccounts() {
        return String.format("Total accounts: %d", totalAccounts);
    }

    //Update an account holder method
    public String updateAccountHolder(String newHolder) {
        if (newHolder == null || newHolder.isBlank()) {
            throw new InvalidAccountHolderException("Invalid holder name.");
        }
        this.accountHolder = newHolder;
        return String.format("Now the new holder is %s.", newHolder);
    }
}
