package service;

import model.Expense;

public class ApprovalService {

    public void approveExpense(Expense expense, int level) {
        if (level == 1) {
            expense.setStatus("MANAGER APPROVED");
        } else if (level == 2) {
            expense.setStatus("FINANCE APPROVED");
        }
    }
}
