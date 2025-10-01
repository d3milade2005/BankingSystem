package org.bankapp;

import org.bankapp.model.*;
import org.bankapp.repository.FileAccountRepository;
import org.bankapp.repository.AccountRepository;
import org.bankapp.repository.FileTransactionRepository;
import org.bankapp.repository.TransactionRepository;
import org.bankapp.service.BankService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountRepository accRepo = new FileAccountRepository();
        TransactionRepository tranRepo= new FileTransactionRepository();
        BankService bank = new BankService(accRepo, tranRepo);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Welcome to Demi's Bank ---");
            System.out.println("1. Create savings account");
            System.out.println("2. Create current account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer");
            System.out.println("6. Show account");
            System.out.println("0. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter your Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter your Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter your Phone Number: ");
                    String phone = scanner.nextLine();

                    Customer customer = new Customer(name, email, phone);
                    SavingsAccount userSavingsAccount = bank.createSavingsAccount(customer, 0.05);

                    System.out.println("Savings account created successfully! Your account Number is: " + userSavingsAccount.getAccountNumber());
                    break;
                case "2":
                    System.out.print("Enter your name: ");
                    String currentName = scanner.nextLine();

                    System.out.print("Enter your email: ");
                    String currentEmail = scanner.nextLine();

                    System.out.print("Enter your phone number: ");
                    String currentPhone = scanner.nextLine();

                    Customer currentOwner = new Customer(currentName, currentEmail, currentPhone);

                    CurrentAccount userCurrentAccount = bank.createCurrentAccount(currentOwner, 0.05);

                    System.out.println("Current account created successfully! Your account number is: " + userCurrentAccount.getAccountNumber());
                    break;
                case "3":
                    System.out.print("Enter your account number: ");
                    String accountNumber = scanner.nextLine();

                    System.out.print("Enter the amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    bank.deposit(accountNumber, amount);
                    break;
                case "4":
                    System.out.print("Enter your account number: ");
                    String accountNumber1 = scanner.nextLine();

                    System.out.print("Enter the amount: ");
                    double amount1 = scanner.nextDouble();
                    scanner.nextLine();

                    bank.withdraw(accountNumber1, amount1);
                    break;
                case "5":
                    System.out.print("Enter your account number: ");
                    String fromAccountNumber = scanner.nextLine();

                    System.out.print("Enter the account number you want to transfer to: ");
                    String toAccountNumber = scanner.nextLine();

                    System.out.print("Enter the amount: ");
                    double amountToSend = scanner.nextDouble();
                    scanner.nextLine();

                    bank.transfer(fromAccountNumber, toAccountNumber,amountToSend);
                    break;
                case "6":
                    System.out.print("Enter your account number: ");
                    String accountNumber2 = scanner.nextLine();

                    List<Transaction> transactions = bank.getTransactions(accountNumber2);
                    Account accountDetails = bank.getAccountDetails(accountNumber2);

                    System.out.println("=== Account Details ===");
                    System.out.println("Account Number: " + accountDetails.getAccountNumber());
                    System.out.println("Owner: " + accountDetails.getOwner().getCustomerName());
                    System.out.println("Balance: " + accountDetails.getBalance());
                    System.out.println();

                    System.out.println("=== Transactions ===");
                    for (Transaction t : transactions) {
                        System.out.println(
                                t.getTimestamp() + " | " +
                                        t.getType() + " | " +
                                        "Amount: " + t.getAmount() +
                                        (t.getFromAccount() != null ? " | From: " + t.getFromAccount() : "") +
                                        (t.getToAccount() != null ? " | To: " + t.getToAccount() : "") +
                                        " | " + t.getDescription()
                        );
                    }
                    break;
                case "0":
                    System.out.println("Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
