package model;

import java.time.LocalDateTime;

public class FinanceTransaction {

    private int expenseId;
    private double amount;
    private String paymentStatus;
    private LocalDateTime processedAt;

    public FinanceTransaction(int expenseId, double amount) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.paymentStatus = "INITIATED";
        this.processedAt = LocalDateTime.now();
    }

    public void markPaid() {
        this.paymentStatus = "PAID";
    }

    public int getExpenseId() {
        return expenseId;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public LocalDateTime getProcessedAt() {
        return processedAt;
    }
}
