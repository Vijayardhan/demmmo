import model.*;
import service.*;
import exception.PolicyViolationException;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            // ---------- EMPLOYEE DETAILS ----------
            System.out.print("Enter Employee ID: ");
            int empId = sc.nextInt();
            sc.nextLine();

            System.out.print("Enter Employee Name: ");
            String empName = sc.nextLine();

            Employee emp = new Employee(empId, empName);

            ExpenseService expenseService = new ExpenseService();
            ApprovalService approvalService = new ApprovalService();
            FinanceService financeService = new FinanceService(); // ✅ Enhanced FSI

            Set<Integer> expenseIdSet = new HashSet<>();
            Set<String> categorySet = new HashSet<>();

            boolean addAnother = true;

            // ---------- EXPENSE INPUT LOOP ----------
            while (addAnother) {

                System.out.print("\nEnter Expense ID: ");
                int expenseId = sc.nextInt();
                sc.nextLine();

                if (expenseIdSet.contains(expenseId)) {
                    throw new RuntimeException("Duplicate Expense ID not allowed!");
                }

                System.out.print("Enter Expense Amount: ");
                double amount = sc.nextDouble();
                sc.nextLine();

                System.out.print("Enter Expense Category (TRAVEL / FOOD): ");
                String category = sc.nextLine().toUpperCase();

                if (categorySet.contains(category)) {
                    throw new RuntimeException("Duplicate Expense Category not allowed!");
                }

                Expense expense;

                if (category.equals("TRAVEL")) {
                    
                    expense = new TravelExpense(expenseId, amount);

                } else if (category.equals("FOOD")) {
                    expense = new FoodExpense(expenseId, amount);

                } else {
                    System.out.println("Invalid expense category.");
                    continue;
                }

                // ---------- RECEIPT FILE INPUT ----------
                System.out.print("Enter Receipt File Name (e.g., receipt.txt): ");
                String receiptFileName = sc.nextLine();

                Receipt receipt = new Receipt(receiptFileName);
                expense.setReceipt(receipt);

                // ---------- STORE & SUBMIT ----------
                emp.addExpense(expense);
                expenseIdSet.add(expenseId);
                categorySet.add(category);

                expenseService.submitExpense(expense, category);

                // ---------- APPROVAL WORKFLOW ----------
                approvalService.approveExpense(expense, 1);
                approvalService.approveExpense(expense, 2);

                /*---------- FINANCE SYSTEM INTEGRATION ----------
                financeService.processPayment(expense);*/

                System.out.println("\nExpense Submitted Successfully!");
                System.out.println("Reimbursement Amount: " + expense.calculateReimbursement());
                System.out.println("Approval Status: " + expense.getStatus());
                System.out.println("");
                
             // ---------- FINANCE SYSTEM INTEGRATION ----------
                financeService.processPayment(expense);

                System.out.print("\nDo you want to submit another expense category? (yes/no): ");
                String choice = sc.nextLine();

                if (!choice.equalsIgnoreCase("yes")) {
                    addAnother = false;
                }
            }

            // ---------- FINAL SUMMARY ----------
            System.out.println("\nExpense Summary for " + empName);
            for (Expense e : emp.getExpenses()) {
                System.out.println(
                        "Expense ID: " + e.getExpenseId() +
                        ", Amount: " + e.getAmount() +
                        ", Reimbursement: " + e.calculateReimbursement() +
                        ", Status: " + e.getStatus()
                );
            }

        } catch (PolicyViolationException e) {
            System.out.println("Policy Error: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println("Input Error: " + e.getMessage());

        } catch (RuntimeException e) {
            System.out.println("Validation Error: " + e.getMessage());

        } finally {
            sc.close();
        }
    }
}
