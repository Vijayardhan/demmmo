package model;

public class TravelExpense extends Expense {


    public TravelExpense(int id, double amount) {
        super(id, amount);

    }

    @Override
    public double calculateReimbursement() {
        return getAmount() * 0.8;
    }
}
