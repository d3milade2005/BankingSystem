package org.bankapp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Account {
    protected final String accountNumber;
    protected final Customer owner;
    protected double balance;
    protected final AccountType type;
    protected final List<Transaction> transactions = new ArrayList<>();

    public Account(String accountNumber, Customer owner, AccountType type) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.type = type;
        this.balance = 0.0;
    }

    public String getAccountNumber() { return accountNumber; }
    public Customer getOwner() { return owner; }
    public double getBalance() { return balance; }
    public AccountType getType() { return type; }
    public List<Transaction> getTransactions() { return Collections.unmodifiableList(transactions); }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Helper to record transaction (no business rules here)
    protected void recordTransaction(Transaction tx) {
        transactions.add(tx);
    }

    @Override
    public String toString() {
        return String.format("%s - %s [%.2f]", accountNumber, owner.getCustomerName(), balance);
    }
}
