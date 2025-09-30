package org.bankapp.service;

import org.bankapp.model.CurrentAccount;
import org.bankapp.model.Customer;
import org.bankapp.model.SavingsAccount;
import org.bankapp.model.Transaction;
import org.bankapp.utils.AccountNumberGenerator
import org.bankapp.repository.AccountRepository;

import java.util.List;

public class BankService {
    private final AccountRepository accountRepository;

    public BankService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public SavingsAccount createSavingsAccount(Customer owner, double interestRate) {
        String accNo = AccountNumberGenerator.generate();
        SavingsAccount acc = new SavingsAccount(accNo, owner, interestRate);
        accountRepo.save(acc);
        return acc;
    }

    public CurrentAccount createCurrentAccount(Customer owner, double overdraftLimit) {
        String accNo = AccountNumberGenerator.generate();
        CurrentAccount acc = new CurrentAccount(accNo, owner, overdraftLimit);
        accountRepository.save(acc);
        return acc;
    }

    public void deposit(String accountNumber, double amount) {
        throw new UnsupportedOperationException("TODO: implement deposit logic (validate, update balance, record transaction)");
    }

    public void withdraw(String accountNumber, double amount) {
        throw new UnsupportedOperationException("TODO: implement withdraw logic (check funds/overdraft, update, record)");
    }

    public void transfer(String fromAccount, String toAccount, double amount) {
        throw new UnsupportedOperationException("TODO: implement atomic transfer (withdraw + deposit with locking)");
    }

    public List<Transaction> getTransactions(String accountNumber) {
        throw new UnsupportedOperationException("TODO: return transactions for account");
    }
}
