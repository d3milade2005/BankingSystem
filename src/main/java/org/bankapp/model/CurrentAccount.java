package org.bankapp.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrentAccount extends Account{
    public double overDraftLimit;

    @JsonCreator
    public CurrentAccount(
            @JsonProperty("accountNumber") String accountNumber,
            @JsonProperty("owner") Customer owner,
            @JsonProperty("overDraftLimit") double overDraftLimit) {
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
