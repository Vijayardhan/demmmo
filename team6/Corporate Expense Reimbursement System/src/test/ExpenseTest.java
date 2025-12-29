package test;

import static org.junit.jupiter.api.Assertions.*;

import model.*;
import service.*;
import exception.PolicyViolationException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

public class ExpenseTest {

    private static final String RECEIPT_FILE = "receipt.txt";

    // ---------- Create receipt file once ----------
    @BeforeAll
    static void setupReceiptFile() {
        try (FileWriter writer = new FileWriter(
                System.getProperty("user.dir") + "/src/" + RECEIPT_FILE)) {

            // Values consumed sequentially by Receipt
            writer.write("1000\n");
            writer.write("2000\n");
            writer.write("3000\n");
            writer.write("1500\n");
            writer.write("2500\n");

        } catch (IOException e) {
            fail("Failed to create receipt file for tests");
        }
    }

    // Travel reimbursement calculation
    @Test
    public void testTravelReimbursement() {
        Expense e = new TravelExpense(1, 4000);
        assertTrue(e.calculateReimbursement() > 0);
    }

    // Food reimbursement calculation
    @Test
    public void testFoodReimbursement() {
        Expense e = new FoodExpense(2, 1000);
        assertEquals(600, e.calculateReimbursement());
    }

    // Receipt mandatory validation
    @Test
    public void testReceiptMandatory() {
        ExpenseService service = new ExpenseService();
        Expense e = new FoodExpense(3, 1000);

        assertThrows(PolicyViolationException.class, () ->
                service.submitExpense(e, "FOOD"));
    }

    // Receipt amount mismatch
    @Test
    public void testReceiptAmountMismatch() {
        ExpenseService service = new ExpenseService();
        Expense e = new FoodExpense(4, 2000);

        e.setReceipt(new Receipt(RECEIPT_FILE)); // takes 1000

        assertThrows(PolicyViolationException.class, () ->
                service.submitExpense(e, "FOOD"));
    }

    // Expense default status
    @Test
    public void testInitialExpenseStatus() {
        Expense e = new FoodExpense(7, 1000);
        assertEquals("SUBMITTED", e.getStatus());
    }

    // Finance system integration test
    @Test
    public void testFinanceSystemIntegration() {
        Expense e = new FoodExpense(10, 1000);
        FinanceService financeService = new FinanceService();

        assertDoesNotThrow(() -> financeService.processPayment(e));
    }

    // Receipt getter test
    @Test
    public void testReceiptGetter() {
        Receipt r = new Receipt(RECEIPT_FILE);
        assertTrue(r.getReceiptAmount() > 0);
    }
    

    // Travel reimbursement should be less than expense amount
    @Test
    public void testTravelReimbursementLessThanAmount() {
        Expense e = new TravelExpense(15, 4000);
        assertTrue(e.calculateReimbursement() < e.getAmount());
    }

    // Food reimbursement should be less than expense amount
    @Test
    public void testFoodReimbursementLessThanAmount() {
        Expense e = new FoodExpense(16, 1000);
        assertTrue(e.calculateReimbursement() < e.getAmount());
    }

    // Approval Level 1 status update
    @Test
    public void testApprovalLevelOne() {
        Expense e = new FoodExpense(17, 1000);
        ApprovalService approvalService = new ApprovalService();

        approvalService.approveExpense(e, 1);
        assertEquals("MANAGER APPROVED", e.getStatus());
    }


    // Approval Level 2 status update
    @Test
    public void testApprovalLevelTwo() {
        Expense e = new FoodExpense(18, 1000);
        ApprovalService approvalService = new ApprovalService();

        approvalService.approveExpense(e, 2);
        assertEquals("FINANCE APPROVED", e.getStatus());
    }



    // Finance payment should not throw exception after approval
    @Test
    public void testFinancePaymentAfterApproval() {
        Expense e = new FoodExpense(19, 1000);
        ApprovalService approvalService = new ApprovalService();
        FinanceService financeService = new FinanceService();

        approvalService.approveExpense(e, 1);
        approvalService.approveExpense(e, 2);

        assertDoesNotThrow(() -> financeService.processPayment(e));
    }

}
