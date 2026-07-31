# Changelog

All notable changes to this project will be documented in this file.

## [Unreleased]

### Added
- `InvalidAccountHolderException`: new custom exception for invalid account holder names (blank/null).
- `BankAccount.updateAccountHolder(String)`: public method to safely update the account holder's name, with validation (rejects null/blank names) instead of allowing direct, unchecked field mutation.
- `BankAccount.totalAccounts` (static) + `BankAccount.getTotalAccounts()`: tracks the total number of accounts created across all instances, demonstrating class-level (static) state vs instance-level state.
- Constructor validation: `BankAccount(String, int)` now throws `InvalidAccountHolderException` if the holder name is null or blank, instead of silently accepting invalid data.

### Changed
- `BankAccount` is now an `abstract class`. It can no longer be instantiated directly — it only makes sense as a base for `CurrentAccount` and `SavingsAccount`.
- `BankAccount.withdraw(double)` is now `abstract`. Each subclass defines its own withdrawal rule:
  - `CurrentAccount`: allows negative balance up to `overdraftLimit`.
  - `SavingsAccount`: does not allow negative balance.
- `BankAccount.accountNumber` is now `final` — an account's number should never change after creation.
- `BankAccount.transfer(...)`: merged the two nearly-identical `catch` blocks (`InsufficientFundsException`, `InvalidAmountException`) into a single multi-catch clause (`catch (InsufficientFundsException | InvalidAmountException e)`), removing duplicated code.

## [1.1.0]

### Added
- `transfer(BankAccount destination, double amount)` method in `BankAccount`, allowing money to be moved between two accounts by reusing the existing `withdraw`/`deposit` logic (polymorphism-friendly — works correctly regardless of the concrete account type).
- Custom exceptions `InsufficientFundsException` and `InvalidAmountException`, replacing generic error handling with specific, descriptive error types.

### Changed
- `withdraw(double amount)` no longer returns a `boolean`. It now returns a `String` (a success message) on success, and throws a custom exception (`InsufficientFundsException` or `InvalidAmountException`) on failure. This allows calling code to distinguish between different failure reasons and react accordingly, instead of only knowing that "something failed."
- `setBalance(double amount)` (protected) now overwrites the balance directly instead of adding to it. Callers are responsible for computing the final value before calling it (e.g. `setBalance(getBalance() + amount)`).

### Why
The previous `boolean`-based `withdraw` made it impossible to communicate *why* a withdrawal failed, which limited the user experience when building `transfer()`. Moving to exceptions makes error handling explicit and extensible, and reflects a more standard Java practice for signaling failure conditions.

## [1.0.0] - Initial Release

### Added
- `BankAccount` superclass with encapsulated balance, deposit, withdraw, and balance check functionality.
- `CurrentAccount` subclass with overdraft limit support.
- `SavingsAccount` subclass with interest accrual and stricter withdrawal rules (no negative balance).
- Demonstrated core OOP concepts: encapsulation, inheritance, and polymorphism.
