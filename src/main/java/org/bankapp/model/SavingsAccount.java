package org.bankapp.model;

public class SavingsAccount extends Account{
    private double interestRate;

    public SavingsAccount(String accountNumber, Customer owner, double interestRate) {
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
