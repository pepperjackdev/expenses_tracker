package io.github.pepperjackdev.expensestracker.expenses;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// TODO: Use mockito to mock the database instead of using a test database

public class ExpensesTest { 
    private Expenses expenses;

    @BeforeEach
    void setUp() {
        // Assuming Expenses class has a way to work in a test mode or with a test database
        expenses = new Expenses("test_database.db");

        // let's clear the database before each test
        expenses.deleteAllExpenses();

        expenses.addNewExpense(10.0, "Coffee", LocalDate.now(), "Food");
        expenses.addNewExpense(20.0, "Gas", LocalDate.now(), "Transportation");
        expenses.addNewExpense(30.0, "Shoes", LocalDate.now(), "Clothing");
        expenses.addNewExpense(40.0, "Books", LocalDate.now(), "Education");
        expenses.addNewExpense(50.0, "Gym", LocalDate.now(), "Health");
    }

    @Test
    void testAddNewExpense() {
        expenses.addNewExpense(60.0, "Music", LocalDate.now(), "Entertainment");
        List<Expense> result = expenses.getAllExpenses();
        assertTrue(result.stream().anyMatch(e -> e.getDescription().equals("Music") && e.getAmount() == 60.0 && e.getCategory().equals("Entertainment")));
    }

    @Test
    void testGetAllExpenses() {
        List<Expense> result = expenses.getAllExpenses();
        assertEquals(5, result.size());
    }

    @Test
    void testGetAllExpensesOfCategory() {
        List<Expense> result = expenses.getAllExpensesOfCategory("Food");
        assertEquals(1, result.size());
        assertTrue(result.stream().allMatch(e -> e.getCategory().equals("Food")));
    }

    @Test
    void testGetAllExpensesOfCategoryAndDateRange() {
        List<Expense> result = expenses.getAllExpensesOfCategoryAndDateRange("Clothing", LocalDate.now(), LocalDate.now());
        assertEquals(1, result.size());
        assertTrue(result.stream().allMatch(e -> e.getCategory().equals("Clothing")));
    }

    @Test
    void testDeleteExpense() {
        List<Expense> result = expenses.getAllExpenses();
        Expense expense = result.get(0);
        expenses.deleteExpense(expense);
        result = expenses.getAllExpenses();
        assertTrue(result.stream().noneMatch(e -> e.getId().equals(expense.getId())));
    }

    @Test
    void testDeleteAllExpenses() {
        expenses.deleteAllExpenses();
        List<Expense> result = expenses.getAllExpenses();
        assertTrue(result.isEmpty());
    }
}
