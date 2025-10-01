package org.bankapp.service;

import org.bankapp.model.*;
import org.bankapp.repository.TransactionRepository;
import org.bankapp.utils.AccountNumberGenerator;
import org.bankapp.repository.AccountRepository;
import org.bankapp.exceptions.AccountNotFoundException;
import org.bankapp.exceptions.InsufficientFundsException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class BankService {
    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public BankService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    public SavingsAccount createSavingsAccount(Customer owner, double interestRate) {
        String accNo = AccountNumberGenerator.generate();
        SavingsAccount acc = new SavingsAccount(accNo, owner, interestRate);
        accountRepository.save(acc);
        return acc;
    }

    public CurrentAccount createCurrentAccount(Customer owner, double overdraftLimit) {
        String accNo = AccountNumberGenerator.generate();
        CurrentAccount acc = new CurrentAccount(accNo, owner, overdraftLimit);
        accountRepository.save(acc);
        return acc;
    }

    public void deposit(String accountNumber, double amount) {
        try {
            Account account = accountRepository.findByNumber(accountNumber);
            if (account == null) {
                throw new AccountNotFoundException("Account " + accountNumber + " not found.");
            }

            account.setBalance(account.getBalance() + amount);
            accountRepository.save(account);

            Transaction tx = new Transaction(
                    UUID.randomUUID().toString(),
                    LocalDateTime.now(),
                    Transaction.Type.DEPOSIT,
                    amount,
                    null,
                    accountNumber,
                    "Deposit to account " + accountNumber
            );
            transactionRepository.save(tx);

            System.out.println("Deposited " + amount + " into " + accountNumber);
        } catch (Exception e) {
            System.err.println("Deposit failed: " + e.getMessage());
        }
    }

    public void withdraw(String accountNumber, double amount) {
        try {
            Account account = accountRepository.findByNumber(accountNumber);
            if (account == null) {
                throw new AccountNotFoundException("Account " + accountNumber + " not found.");
            }

            if (account.getBalance() < amount) {
                throw new InsufficientFundsException("Insufficient funds in account " + accountNumber);
            }

            account.setBalance(account.getBalance() - amount);
            accountRepository.save(account);

            Transaction tx = new Transaction(
                    UUID.randomUUID().toString(),
                    LocalDateTime.now(),
                    Transaction.Type.WITHDRAWAL,
                    amount,
                    accountNumber,
                    null,
                    "Withdraw from account " + accountNumber
            );
            transactionRepository.save(tx);

            System.out.println("Withdrew " + amount + " from " + accountNumber);
        } catch (Exception e) {
            System.err.println("Withdrawal failed: " + e.getMessage());
        }
    }

    public void transfer(String fromAccount, String toAccount, double amount) {
        try {
            Account fromAcc = accountRepository.findByNumber(fromAccount);
            Account toAcc = accountRepository.findByNumber(toAccount);

            if (fromAcc == null) throw new AccountNotFoundException("Sender account not found: " + fromAccount);
            if (toAcc == null) throw new AccountNotFoundException("Receiver account not found: " + toAccount);
            if (fromAcc.getBalance() < amount) throw new InsufficientFundsException("Insufficient funds in " + fromAccount);

            fromAcc.setBalance(fromAcc.getBalance() - amount);
            accountRepository.save(fromAcc);

            toAcc.setBalance(toAcc.getBalance() + amount);
            accountRepository.save(toAcc);

            Transaction tx = new Transaction(
                    UUID.randomUUID().toString(),
                    LocalDateTime.now(),
                    Transaction.Type.TRANSFER,
                    amount,
                    fromAccount,
                    toAccount,
                    "Transfer from account " + fromAccount + " to account " + toAccount
            );
            transactionRepository.save(tx);

            System.out.println("Transferred " + amount + " from " + fromAccount + " to " + toAccount);
        } catch (Exception e) {
            System.err.println("Transfer failed: " + e.getMessage());
        }
    }

    public List<Transaction> getTransactions(String accountNumber) {
        try {
            return (List<Transaction>) transactionRepository.findByAccountNumber(accountNumber);
        } catch (Exception e) {
            System.err.println("Could not fetch transactions: " + e.getMessage());
            return List.of();
        }
    }

    public Account getAccountDetails(String accountNumber) {
        Account account = accountRepository.findByNumber(accountNumber);
        if (account == null) {
            throw new AccountNotFoundException("Account " + accountNumber + " not found.");
        }
        return account;
    }
}