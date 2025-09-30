package org.bankapp;

import org.bankapp.model.Transaction;
import org.bankapp.repository.FileAccountRepository;
import org.bankapp.repository.AccountRepository;
import org.bankapp.repository.FileTransactionRepository;
import org.bankapp.repository.TransactionRepository;
import org.bankapp.service.BankService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AccountRepository accRepo = new FileAccountRepository();
        TransactionRepository tranRepo= new FileTransactionRepository();
        BankService bank = new BankService(accRepo, tranRepo);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Simple Bank ---");
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
                    System.out.println("TODO: read customer details, call bank.createSavingsAccount()");
                    break;
                case "2":
                    System.out.println("TODO: read customer details, call bank.createCurrentAccount()");
                    break;
                case "3":
                    System.out.println("TODO: read account + amount, call bank.deposit()");
                    break;
                case "4":
                    System.out.println("TODO: read account + amount, call bank.withdraw()");
                    break;
                case "5":
                    System.out.println("TODO: read from, to, amount, call bank.transfer()");
                    break;
                case "6":
                    System.out.println("TODO: print account details and transaction history");
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
