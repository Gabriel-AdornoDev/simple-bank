package org.gabriel.simplebank;

public class CurrentAccount extends BankAccount {
    double overdraftLimit = 500.0;

    //Constructor method
    public CurrentAccount(String accountHolder, int accountNumber, double overdraftLimit) {
        super(accountHolder, accountNumber);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public String withdraw(double amount) {
        if (amount <= 0) {
            return String.format("%.2f is an invalid amount.", amount);
        }
        else if (amount > (getBalance() + overdraftLimit)) {
            return String.format("%.2f is more than your balance and limit.", amount);
        }
        else {
            double previousBalance = getBalance();
            debit(amount);
            return String.format("Previous balance: %.2f\nCurrent balance: %.2f", previousBalance, getBalance());
        }
    }
}

