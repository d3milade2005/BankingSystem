package org.bankapp.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Customer {
    private final String customerId;
    private String customerName;
    private String customerEmail;
    private String customerPhoneNumber;

    @JsonCreator
    public Customer(
            @JsonProperty("customerId") String customerId,
            @JsonProperty("customerName") String customerName,
            @JsonProperty("customerEmail") String customerEmail,
            @JsonProperty("customerPhoneNumber") String customerPhoneNumber) {
        this.customerId = UUID.randomUUID().toString();
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerId() {
        return customerId;
    }
    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerPhoneNumber='" + customerPhoneNumber + '\'' +
                '}';
    }
}
