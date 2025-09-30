package org.bankapp.repository;

import org.bankapp.model.Account;

import java.util.Collection;

public interface AccountRepository {
    void save(Account account);
    Account findByNumber(String accountNumber);
    Collection<Account> findAll();
    void delete(String accountNumber);
}
