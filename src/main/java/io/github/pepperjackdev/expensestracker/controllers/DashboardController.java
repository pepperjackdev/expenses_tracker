package io.github.pepperjackdev.expensestracker.controllers;

import static io.github.pepperjackdev.expensestracker.App.expenses;

import io.github.pepperjackdev.expensestracker.expenses.Expense;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class DashboardController {
    
    @FXML Label totalMonthAmount;
    @FXML ListView<Expense> lastExpenses;
    @FXML ListView<String> mostTargettedCategories;

    public void initialize() {
        totalMonthAmount.setText(String.valueOf(expenses.getAllExpenses().stream()
                .mapToDouble(expense -> expense.getAmount())
                .sum()));

        lastExpenses.getItems().addAll(expenses.getLastExpenses(10)); // Let's show the last 10 expenses
        mostTargettedCategories.getItems().addAll(expenses.getMostTargettedCategories());
    }
}
