package org.bankapp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SavingsAccount extends Account{
    private double interestRate;

    @JsonCreator
    public SavingsAccount(
            @JsonProperty("accountNumber") String accountNumber,
            @JsonProperty("owner") Customer owner,
            @JsonProperty("interestRate") double interestRate) {
        super(accountNumber, owner, AccountType.SAVINGS);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
