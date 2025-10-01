package org.bankapp.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SavingsAccount.class, name = "SAVINGS"),
        @JsonSubTypes.Type(value = CurrentAccount.class, name = "CURRENT")
})
public abstract class Account {
    protected final String accountNumber;
    protected final Customer owner;
    protected double balance;
    protected final AccountType type;

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
    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return String.format("%s - %s [%.2f]", accountNumber, owner.getCustomerName(), balance);
    }
}
