# Changelog

All notable changes to this project will be documented in this file.

## [Unreleased]

### Added
- `InvalidAccountHolderException`: new custom exception for invalid account holder names (blank/null), following the same pattern as `InvalidAmountException` and `InsufficientFundsException`.
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
