package org.bankapp.service;

import org.bankapp.model.*;
import org.bankapp.repository.TransactionRepository;
import org.bankapp.utils.AccountNumberGenerator;
import org.bankapp.repository.AccountRepository;

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
        Account account = accountRepository.findByNumber(accountNumber);
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
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
        System.out.println("✅ Deposited " + amount + " into " + accountNumber);
    }

    public void withdraw(String accountNumber, double amount) {
        Account account = accountRepository.findByNumber(accountNumber);

        if (account.getBalance() < 0 || account.getBalance() < amount) {
            System.out.println("Insufficient Funds");
        }
        double newBalance = account.getBalance() - amount;
        account.setBalance(newBalance);
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
        System.out.println("✅ Withdrew \" + amount + \" from \" + accountNumber");
    }

    public void transfer(String fromAccount, String toAccount, double amount) {
        Account fromAcc = accountRepository.findByNumber(fromAccount);
        Account toAcc = accountRepository.findByNumber(toAccount);

        if (fromAcc.getBalance() < amount || fromAcc.getBalance() <= 0) {
            System.out.println("Insufficient Funds");
        }

        double newBalance = fromAcc.getBalance() - amount;
        fromAcc.setBalance(newBalance);
        accountRepository.save(fromAcc);

        double newToBalance = toAcc.getBalance() + amount;
        toAcc.setBalance(newToBalance);
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
    }

    public List<Transaction> getTransactions(String accountNumber) {
        return (List<Transaction>) transactionRepository.findByAccountNumber(accountNumber);
    }

    public Account getAccountDetails(String accountNumber) {
        return accountRepository.findByNumber(accountNumber);
    }
}
