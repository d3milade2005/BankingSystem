package org.bankapp.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bankapp.model.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FileTransactionRepository implements TransactionRepository {
    private static final String FILE_PATH = "transactions.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private List<Transaction> transactions = new ArrayList<>();

    public FileTransactionRepository() {
        loadFromFile();
    }

    @Override
    public void save(Transaction transaction) {
        transactions.add(transaction);
        writeToFile();
    }

    @Override
    public Collection<Transaction> findAll() {
        return transactions;
    }

    @Override
    public Collection<Transaction> findByAccountNumber(String accountNumber) {
        return transactions.stream()
                .filter(t -> accountNumber.equals(t.getFromAccount()) || accountNumber.equals(t.getToAccount()))
                .collect(Collectors.toList());
    }

    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (file.exists() && file.length() > 0) {
            try {
                transactions = mapper.readValue(file, new TypeReference<List<Transaction>>() {});
            } catch (IOException e) {
                System.out.println("Could not load transactions: " + e.getMessage());
            }
        }
    }

    private void writeToFile() {
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), transactions);
        } catch (IOException e) {
            System.out.println("Could not save transactions: " + e.getMessage());
        }
    }
}
