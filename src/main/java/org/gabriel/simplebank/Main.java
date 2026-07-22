package org.gabriel.simplebank;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== Creating accounts ===");
        CurrentAccount checking = new CurrentAccount("Gabriel", 1, 500);
        SavingsAccount savings = new SavingsAccount("Gabriel2", 2);

        System.out.println("\n=== Deposits ===");
        System.out.println(checking.deposit(500));
        System.out.println(savings.deposit(123));

        System.out.println("\n=== Invalid deposit ===");
        System.out.println(checking.deposit(-50));

        System.out.println("\n=== Normal withdrawals ===");
        System.out.println(checking.withdraw(400));
        System.out.println(savings.withdraw(50));

        System.out.println("\n=== Overdraft boundary test (exactly at limit) ===");
        System.out.println(checking.withdraw(200)); // balance: 100 -> -100
        System.out.println(checking.withdraw(400)); // balance: -100 -> -500 (exactly at overdraft limit)

        System.out.println("\n=== Overdraft exceeded ===");
        System.out.println(checking.withdraw(1)); // should be rejected, would exceed -500

        System.out.println("\n=== Savings account rejecting negative balance ===");
        System.out.println(savings.withdraw(1000)); // should be rejected

        System.out.println("\n=== Invalid withdrawal amount ===");
        System.out.println(checking.withdraw(-10));

        System.out.println("\n=== Applying interest ===");
        System.out.println(savings.applyInterest());

        System.out.println("\n=== Final balances ===");
        System.out.println(checking.checkBalance());
        System.out.println(savings.checkBalance());

        System.out.println("\n=== Polymorphism test: iterating over a list of BankAccount ===");
        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(checking);
        accounts.add(savings);

        for (BankAccount account : accounts) {
            System.out.println(account.getAccountHolder() + " (#" + account.getAccountNumber() + "): " + account.checkBalance());
        }
    }
}