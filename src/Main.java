import exception.AccountNotFoundException;
import exception.InsufficientBalanceException;
import service.BankService;

import java.util.Scanner;

public class Main {
    static BankService bank = new BankService();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║     BANKING SYSTEM v1.0      ║");
        System.out.println("╚══════════════════════════════╝");

        while (true) {
            printMenu();
            System.out.print("Enter choice: ");
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1" -> createAccount();
                case "2" -> deposit();
                case "3" -> withdraw();
                case "4" -> transfer();
                case "5" -> viewStatement();
                case "6" -> bank.listAllAccounts();
                case "7" -> {
                    System.out.println("Thank you for using Banking System. Goodbye!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice! Please enter 1-7.");
            }
        }
    }

    static void printMenu() {
        System.out.println("\n==============================");
        System.out.println("  1. Create Account");
        System.out.println("  2. Deposit");
        System.out.println("  3. Withdraw");
        System.out.println("  4. Transfer");
        System.out.println("  5. View Statement");
        System.out.println("  6. List All Accounts");
        System.out.println("  7. Exit");
        System.out.println("==============================");
    }

    static void createAccount() {
        System.out.print("Enter account holder name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter initial deposit amount: ");
        try {
            double amount = Double.parseDouble(sc.nextLine().trim());
            bank.createAccount(name, amount);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered.");
        }
    }

    static void deposit() {
        System.out.print("Enter Account ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Enter deposit amount: ");
        try {
            double amount = Double.parseDouble(sc.nextLine().trim());
            bank.deposit(id, amount);
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered.");
        }
    }

    static void withdraw() {
        System.out.print("Enter Account ID: ");
        String id = sc.nextLine().trim();
        System.out.print("Enter withdrawal amount: ");
        try {
            double amount = Double.parseDouble(sc.nextLine().trim());
            bank.withdraw(id, amount);
        } catch (AccountNotFoundException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered.");
        }
    }

    static void transfer() {
        System.out.print("Enter FROM Account ID: ");
        String fromId = sc.nextLine().trim();
        System.out.print("Enter TO Account ID: ");
        String toId = sc.nextLine().trim();
        System.out.print("Enter transfer amount: ");
        try {
            double amount = Double.parseDouble(sc.nextLine().trim());
            bank.transfer(fromId, toId, amount);
        } catch (AccountNotFoundException | InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered.");
        }
    }

    static void viewStatement() {
        System.out.print("Enter Account ID: ");
        String id = sc.nextLine().trim();
        try {
            bank.printStatement(id);
        } catch (AccountNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}