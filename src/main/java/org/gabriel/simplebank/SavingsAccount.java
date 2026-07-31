package org.gabriel.simplebank;

public class SavingsAccount extends BankAccount {
    //interest Rate
    double interestRate = 0.005;

    //Constructor method
    public SavingsAccount(String accountHolder, int accountNumber) {
        super(accountHolder, accountNumber);
    }

    public String applyInterest() {
        double previousBalance = getBalance();
        setBalance(getBalance() + (getBalance() * interestRate));
        return String.format("Previous balance: %.2f\nCurrent balance: %.2f", previousBalance, getBalance());
    }
    @Override
    public String withdraw(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be greater than 0.");
        }
        else if (amount > getBalance()) {
            throw new InsufficientFundsException("Insufficient balance for this amount.");
        }
        else {
            debit(amount);
            return String.format("%.2f withdrawn successfully.\nCurrent balance: %.2f", amount, getBalance());
        }
    }
}