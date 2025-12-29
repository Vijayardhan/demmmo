package service;

import model.Expense;
import model.FinanceTransaction;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class FinanceService {

    private Map<Integer, FinanceTransaction> transactionMap = new HashMap<>();

    public void processPayment(Expense expense) {

        // Prevent duplicate payments
        if (transactionMap.containsKey(expense.getExpenseId())) {
            System.out.println(
                "Finance System: Payment already processed for Expense ID "
                + expense.getExpenseId()
            );
            return;
        }

        FinanceTransaction transaction =
                new FinanceTransaction(
                        expense.getExpenseId(),
                        expense.calculateReimbursement()
                );

        transaction.markPaid();
        transactionMap.put(expense.getExpenseId(), transaction);

        // ---------- FORMAT DATE & TIME ----------
        LocalDateTime dateTime = transaction.getProcessedAt();

        DateTimeFormatter dateFormatter =
                DateTimeFormatter.ofPattern("yyyy-MM-dd");

        DateTimeFormatter timeFormatter =
                DateTimeFormatter.ofPattern("HH:mm:ss");

        String date = dateTime.format(dateFormatter);
        String time = dateTime.format(timeFormatter);

        System.out.println(
            "Finance System: ₹" + transaction.getAmount() +
            " paid successfully for Expense ID " +
            transaction.getExpenseId() +
            " on Date: " + date +
            " Time: " + time
        );
    }
}
