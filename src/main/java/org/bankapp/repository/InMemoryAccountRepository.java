package org.bankapp.repository;

import org.bankapp.model.Account;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryAccountRepository implements AccountRepository {
    private final ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();

    @Override
    public void save (Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    @Override
    public Account findByNumber(String accountNumber) {
        return accounts.get(accountNumber);
    }

    @Override
    public Collection<Account> findAll() {
        return accounts.values();
    }

    @Override
    public void delete(String accountNumber) {
        accounts.remove(accountNumber);
    }
}
