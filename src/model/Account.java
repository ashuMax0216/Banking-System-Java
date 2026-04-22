package model;

import exception.InsufficientBalanceException;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountId;
    private String ownerName;
    private double balance;
    private List<Transaction> transactions;

    public Account(String accountId, String ownerName, double initialDeposit) {
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>();
        transactions.add(new Transaction("INITIAL DEPOSIT", initialDeposit));
    }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive.");
        balance += amount;
        transactions.add(new Transaction("DEPOSIT", amount));
        System.out.println(" Deposited ₹" + amount + " | New Balance: ₹" + balance);
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive.");
        if (amount > balance) throw new InsufficientBalanceException(
            "❌ Insufficient balance! Available: ₹" + balance + ", Requested: ₹" + amount);
        balance -= amount;
        transactions.add(new Transaction("WITHDRAW", amount));
        System.out.println(" Withdrawn ₹" + amount + " | New Balance: ₹" + balance);
    }

    public void printStatement() {
        System.out.println("\n Statement for: " + ownerName + " [" + accountId + "]");
        System.out.println("─".repeat(50));
        for (Transaction t : transactions) {
            System.out.println(t);
        }
        System.out.println("─".repeat(50));
        System.out.println(" Current Balance: ₹" + balance);
    }

    // Getters
    public String getAccountId() { return accountId; }
    public String getOwnerName() { return ownerName; }
    public double getBalance() { return balance; }
    public List<Transaction> getTransactions() { return transactions; }
}