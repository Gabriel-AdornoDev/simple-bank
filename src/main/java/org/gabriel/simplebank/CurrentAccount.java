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
            throw new InvalidAmountException("Amount must be greater than 0.");
        }
        else if (amount > (getBalance() + overdraftLimit)) {
            throw new InsufficientFundsException("Insufficient balance for this amount.");
        }
        else{
            debit(amount);
            return String.format("%.2f withdrawn successfully.\nCurrent balance: %.2f", amount, getBalance());
        }
    }
}

