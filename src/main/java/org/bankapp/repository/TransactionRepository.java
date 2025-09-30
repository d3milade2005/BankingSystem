package org.bankapp.repository;

import org.bankapp.model.Transaction;

import java.util.Collection;

public interface TransactionRepository {
    void save (Transaction transaction);
    Collection<Transaction> findAll();
    Collection<Transaction> findByAccountNumber(String accountNumber);
}
