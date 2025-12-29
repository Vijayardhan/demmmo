package model;

public class FoodExpense extends Expense {

    public FoodExpense(int id, double amount) {
        super(id, amount);
    }

    @Override
    public double calculateReimbursement() {
        return getAmount() * 0.6;
    }
}
