package service;

import model.Expense;
import model.Policy;
import exception.PolicyViolationException;

import java.util.ArrayList;
import java.util.List;

public class ExpenseService {

    private List<Expense> expenseList = new ArrayList<>();
    private Policy policy = new Policy();

    // Submits an expense after validating receipt and policy rules

    public void submitExpense(Expense expense, String category)
            throws PolicyViolationException {

        // -------- Receipt must be attached --------
        if (expense.getReceipt() == null) {
            throw new PolicyViolationException("Receipt is mandatory!");
        }

        // -------- Receipt amount must match expense amount --------
        double receiptAmount = expense.getReceipt().getReceiptAmount();
        double expenseAmount = expense.getAmount();

        if (receiptAmount != expenseAmount) {
            throw new PolicyViolationException("Receipt amount mismatch!");
        }

        // -------- Policy limit validation --------
        if (expenseAmount > policy.getLimit(category)) {
            throw new PolicyViolationException("Expense exceeds policy limit!");
        }

        // -------- All validations passed --------
        expenseList.add(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseList;
    }
}
