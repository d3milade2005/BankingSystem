package org.bankapp.model;

public class CurrentAccount extends Account{
    public double overDraftLimit;

    public CurrentAccount(String accountNumber, Customer owner, double overDraftLimit) {
        super(accountNumber, owner, AccountType.CURRENT);
        this.overDraftLimit = overDraftLimit;
    }

    public double getOverDraftLimit() {
        return overDraftLimit;
    }

    public void setOverDraftLimit(double overDraftLimit) {
        this.overDraftLimit = overDraftLimit;
    }
}
