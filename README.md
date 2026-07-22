# Simple Bank System

A simple banking system built with pure Java (no frameworks) to practice and demonstrate core Object-Oriented Programming concepts: **Encapsulation**, **Inheritance**, and **Polymorphism**.

## Overview

This project simulates a basic banking system with two types of bank accounts, each with its own withdrawal rules. It was built as a hands-on OOP learning exercise, focusing on clean class design, proper encapsulation, and method overriding.

## Features

- Create bank accounts with an account holder name and account number
- Deposit funds, with validation against invalid (non-positive) amounts
- Withdraw funds, with account-specific business rules
- Check current balance (formatted for display)
- Two distinct account types with different withdrawal behavior:
  - **Current Account** — supports an overdraft limit
  - **Savings Account** — does not allow a negative balance, and can accrue interest

## Class Structure

### `BankAccount` (superclass)

The base class representing a generic bank account.

**Fields:**
| Field | Type | Access | Description |
|---|---|---|---|
| `accountHolder` | `String` | private | Name of the account owner |
| `accountNumber` | `int` | private | Unique account identifier |
| `balance` | `double` | private | Current account balance |

**Methods:**
| Method | Description |
|---|---|
| `deposit(double amount)` | Adds funds to the balance. Rejects non-positive amounts. |
| `withdraw(double amount)` | Subtracts funds from the balance, only if sufficient funds are available. |
| `checkBalance()` | Returns a formatted string with the current balance. |
| `getBalance()` | Returns the raw balance value (for calculations). |
| `getAccountHolder()` | Returns the account holder's name. |
| `getAccountNumber()` | Returns the account number. |
| `debit(double amount)` *(protected)* | Directly decreases the balance. Used internally by subclasses to apply custom withdrawal logic without exposing the balance field. |
| `setBalance(double amount)` *(protected)* | Directly increases the balance. Used internally by subclasses (e.g. to apply interest). |

### `CurrentAccount extends BankAccount`

Represents a checking/current account with overdraft support.

**Extra field:**
- `overdraftLimit` (`double`) — the maximum amount the account can go into debt.

**Overridden behavior:**
- `withdraw(double amount)` — allows the balance to go negative, as long as it does not exceed the overdraft limit.

### `SavingsAccount extends BankAccount`

Represents a savings account with interest accrual.

**Extra field:**
- `interestRate` (`double`) — the interest rate applied to the balance (e.g. `0.005` for 0.5%).

**Inherited behavior:**
- `withdraw(double amount)` — uses the base class implementation, which already prevents the balance from going negative. No override was needed, since the default behavior already satisfies the stricter rule required for savings accounts.

**Extra method:**
- `applyInterest()` — increases the balance by `balance * interestRate`.

## Example Usage

```java
CurrentAccount checking = new CurrentAccount("Gabriel", 1, 500);
SavingsAccount savings = new SavingsAccount("Gabriel2", 2);

checking.deposit(500);
savings.deposit(123);

checking.withdraw(400);   // balance: 100.00
checking.withdraw(200);   // balance: -100.00 (within overdraft limit)
checking.withdraw(5478);  // rejected — exceeds balance + overdraft limit
checking.withdraw(200);   // balance: -300.00

savings.applyInterest();  // balance increased by interestRate

System.out.println(checking.checkBalance());
System.out.println(savings.checkBalance());
```

## OOP Concepts in Practice

- **Encapsulation** — all fields are `private`; access is only possible through public getters and controlled methods (`deposit`, `withdraw`). Internal helper methods (`debit`, `setBalance`) are `protected`, allowing subclasses to modify the balance safely without exposing it publicly.
- **Inheritance** — `CurrentAccount` and `SavingsAccount` both extend `BankAccount`, reusing shared fields and behavior instead of duplicating code.
- **Polymorphism** — `withdraw()` is overridden in `CurrentAccount` to implement overdraft logic, while `SavingsAccount` relies on the inherited version, demonstrating that subclasses can either customize or reuse parent behavior as needed.

## Requirements

- Java 21+ (developed and tested with Java 25)
- No external dependencies — pure Java, no frameworks

## Running the Project

Clone the repository and run the `Main` class from your IDE of choice (IntelliJ IDEA recommended), or compile and run via command line:

```bash
javac org/gabriel/simplebank/*.java -d out
java -cp out org.gabriel.simplebank.Main
```

## Author

Gabriel Adorno — [GitHub](https://github.com/Gabriel-AdornoDev)

## License

This project is open source and available for educational purposes.
