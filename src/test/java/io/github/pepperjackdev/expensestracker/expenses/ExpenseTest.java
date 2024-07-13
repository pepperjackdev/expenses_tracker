package io.github.pepperjackdev.expensestracker.expenses;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExpenseTest {

    private Expenses expenses;
    private Expense expense;

    @BeforeAll
    void initializeTestDatabase() {
        expenses = new Expenses("test_database.db");
    }

    @BeforeEach
    void setUp() {
        expenses.deleteAllExpenses();
        expense = expenses.addNewExpense(1.0, "Coffee", LocalDate.now(), "Food");
    }

    @Test
    void testGetAmount() {
        assertEquals(1.0, expense.getAmount());
    }

    @Test
    void setAmount() {
        expense.setAmount(2.0);
        assertEquals(2.0, expense.getAmount());
    }

    @Test
    void testGetDescription() {
        assertEquals("Coffee", expense.getDescription());
    }

    @Test
    void setDescription() {
        expense.setDescription("Tea");
        assertEquals("Tea", expense.getDescription());
    }

    @Test
    void testGetDate() {
        assertEquals(LocalDate.now(), expense.getDate());
    }

    @Test
    void setDate() {
        LocalDate newDate = LocalDate.of(2021, 1, 1);
        expense.setDate(newDate);
        assertEquals(newDate, expense.getDate());
    }

    @Test
    void testGetCategory() {
        assertEquals("Food", expense.getCategory());
    }

    @Test
    void setCategory() {
        expense.setCategory("Drinks");
        assertEquals("Drinks", expense.getCategory());
    }
}
