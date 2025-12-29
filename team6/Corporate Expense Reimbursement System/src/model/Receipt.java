package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Receipt {

    private static List<Double> receiptAmounts = new ArrayList<>();
    private static int currentIndex = 0;

    private double receiptAmount;
    private String receiptPath;

    public Receipt(String receiptFileName) {

        File file = new File(
                System.getProperty("user.dir") + File.separator + "src" + File.separator + receiptFileName
        );

        if (!file.exists() || !file.isFile()) {
            throw new IllegalArgumentException(
                    "Receipt file not found in src folder: " + receiptFileName
            );
        }

        this.receiptPath = file.getAbsolutePath();

        // Load receipt values only once
        if (receiptAmounts.isEmpty()) {
            loadReceiptAmounts(file);
        }

        // Check if values are exhausted
        if (currentIndex >= receiptAmounts.size()) {
            throw new IllegalArgumentException("No more receipt amounts available in file!");
        }

        // Assign next receipt amount
        this.receiptAmount = receiptAmounts.get(currentIndex);
        currentIndex++;
    }

    // -------- Load all receipt amounts from file --------
    private void loadReceiptAmounts(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                receiptAmounts.add(Double.parseDouble(line.trim()));
            }
        } catch (IOException | NumberFormatException e) {
            throw new IllegalArgumentException("Invalid receipt file content!");
        }
    }

    public double getReceiptAmount() {
        return receiptAmount;
    }

    public String getReceiptPath() {
        return receiptPath;
    }
}
