package org.bankapp.model;

import java.time.LocalDateTime;

public class Transaction {
    public enum Type { DEPOSIT, WITHDRAWAL, TRANSFER};

    private final String id;
    private final LocalDateTime timestamp;
    private final Type type;
    private final double amount;
    private final String fromAccount; // null for deposit
    private final String toAccount; // null for withdrawal
    private final String description;

    public Transaction(String id, LocalDateTime timestamp, Type type, double amount, String fromAccount, String toAccount, String description) {
        this.id = id;
        this.timestamp = timestamp;
        this.type = type;
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Type getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", type=" + type +
                ", amount=" + amount +
                ", fromAccount='" + fromAccount + '\'' +
                ", toAccount='" + toAccount + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
