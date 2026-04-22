package service;

import exception.AccountNotFoundException;
import exception.InsufficientBalanceException;
import model.Account;

import java.util.HashMap;
import java.util.Map;

public class BankService {
    private Map<String, Account> accounts = new HashMap<>();
    private int accountCounter = 1000;

    public Account createAccount(String ownerName, double initialDeposit) {
        String accountId = "ACC" + (++accountCounter);
        Account account = new Account(accountId, ownerName, initialDeposit);
        accounts.put(accountId, account);
        System.out.println(" Account created! ID: " + accountId + " | Owner: " + ownerName);
        return account;
    }

    public Account getAccount(String accountId) throws AccountNotFoundException {
        Account account = accounts.get(accountId);
        if (account == null) throw new AccountNotFoundException(
            " Account not found: " + accountId);
        return account;
    }

    public void deposit(String accountId, double amount) throws AccountNotFoundException {
        getAccount(accountId).deposit(amount);
        FileHandler.logTransaction(accountId, "DEPOSIT", amount);
    }

    public void withdraw(String accountId, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        getAccount(accountId).withdraw(amount);
        FileHandler.logTransaction(accountId, "WITHDRAW", amount);
    }

    public void transfer(String fromId, String toId, double amount)
            throws AccountNotFoundException, InsufficientBalanceException {
        Account from = getAccount(fromId);
        Account to = getAccount(toId);
        from.withdraw(amount);
        to.deposit(amount);
        System.out.println(" Transfer of ₹" + amount + " from " + fromId + " to " + toId);
        FileHandler.logTransaction(fromId, "TRANSFER OUT to " + toId, amount);
        FileHandler.logTransaction(toId, "TRANSFER IN from " + fromId, amount);
    }

    public void printStatement(String accountId) throws AccountNotFoundException {
        getAccount(accountId).printStatement();
    }

    public void listAllAccounts() {
        System.out.println("\n All Accounts:");
        System.out.println("─".repeat(50));
        for (Account a : accounts.values()) {
            System.out.println("ID: " + a.getAccountId() +
                " | Name: " + a.getOwnerName() +
                " | Balance: ₹" + a.getBalance());
        }
        System.out.println("─".repeat(50));
    }
}