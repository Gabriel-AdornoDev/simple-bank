package org.gabriel.simplebank;

public class SavingsAccount extends BankAccount{
    //interest Rate
    double interestRate = 0.005;

    //Constructor method
    public SavingsAccount(String accountHolder, int accountNumber) {
        super(accountHolder, accountNumber);
    }
    public String applyInterest() {
        double previousBalance = getBalance();
        setBalance((getBalance() * interestRate));
        return String.format("Previous balance: %.2f\nCurrent balance: %.2f", previousBalance, getBalance());
    }
}
