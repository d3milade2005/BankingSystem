package org.bankapp.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.bankapp.model.Account;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileAccountRepository implements AccountRepository {
    private final Map<String, Account> accounts = new HashMap<>();
    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("accounts.json");

    public FileAccountRepository() {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        loadFromFile();
    }


    @Override
    public void save(Account account) {
        accounts.put(account.getAccountNumber(), account);
        saveToFile();
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
        saveToFile();
    }

    private void loadFromFile() {
        if (!file.exists()) {
            return; // nothing yet
        }
        try {
            CollectionType type = mapper.getTypeFactory()
                    .constructCollectionType(List.class, Account.class);
            List<Account> list = mapper.readValue(file, type);
            for (Account acc : list) {
                accounts.put(acc.getAccountNumber(), acc);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load accounts", e);
        }
    }

    private void saveToFile() {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(file, accounts.values());
        } catch (IOException e) {
            throw new RuntimeException("Failed to save accounts", e);
        }
    }
}