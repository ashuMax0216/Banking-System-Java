package service;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileHandler {
    private static final String LOG_FILE = "transactions.log";

    public static void logTransaction(String accountId, String type, double amount) {
        try (FileWriter fw = new FileWriter(LOG_FILE, true)) {
            String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            fw.write("[" + timestamp + "] " + accountId + " | " + type + " | ₹" + amount + "\n");
        } catch (IOException e) {
            System.out.println("⚠️ Could not write to log file: " + e.getMessage());
        }
    }
}