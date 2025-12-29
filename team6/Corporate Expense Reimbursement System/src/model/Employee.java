package model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private int empId;
    private String name;
    private List<Expense> expenses = new ArrayList<>();

    public Employee(int empId, String name) {
        this.empId = empId;
        this.name = name;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
}
