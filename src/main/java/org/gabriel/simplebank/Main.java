package org.gabriel.simplebank;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<BankAccount> accounts = new ArrayList<>();

    public static void main(String[] args) {

        System.out.println("=== Creating accounts ===");
        CurrentAccount checking = new CurrentAccount("Gabriel", 1, 500);
        SavingsAccount savings = new SavingsAccount("Gabriel2", 2);
        accounts.add(checking);
        accounts.add(savings);

        System.out.println("\n=== Deposits ===");
        System.out.println(checking.deposit(500));
        System.out.println(savings.deposit(123));

        System.out.println("\n=== Applying interest ===");
        System.out.println(savings.applyInterest());

        System.out.println("\n=== Testing exceptions directly ===");
        try {
            checking.withdraw(999999);
        } catch (InsufficientFundsException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        try {
            checking.withdraw(-10);
        } catch (InvalidAmountException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        System.out.println("\n=== Testing transfer with exceptions ===");
        System.out.println(checking.transfer(savings, 999999)); // should fail
        System.out.println(checking.transfer(savings, 50));     // should succeed

        System.out.println("\n=== Final balances ===");
        System.out.println(checking.checkBalance());
        System.out.println(savings.checkBalance());

        System.out.println("\n=== Polymorphism test: iterating over a list of BankAccount ===");
        for (BankAccount account : accounts) {
            System.out.println(account.getAccountHolder() + " (#" + account.getAccountNumber() + "): " + account.checkBalance());
        }
    }
}