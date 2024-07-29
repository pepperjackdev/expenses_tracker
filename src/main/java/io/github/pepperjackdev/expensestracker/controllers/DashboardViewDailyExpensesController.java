package io.github.pepperjackdev.expensestracker.controllers;

import java.util.List;

import io.github.pepperjackdev.expensestracker.App;
import io.github.pepperjackdev.expensestracker.expenses.Expense;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class DashboardViewDailyExpensesController {
    
    @FXML Label totalAmountSpentTodayBudgetIndicator;
    @FXML Label totalAmountSpentToday;
    @FXML VBox dailyExpensesList;

    @FXML
    void initialize() {

        // -----------------------------------------------------
        // Initializing shown data
        // -----------------------------------------------------

        // --- total amount spent today ---

        // Set the total amount spent today
        totalAmountSpentToday.setText(String.valueOf(
            App.expenses.getTotalAmountOfTodayExpenses()
        ));

        // --- daily budget indicator ---

        // Set the daily budget indicator
        double dailyOverBudget = App.expenses.getTotalAmountOfTodayExpenses() - App.config.getBudget().getDailyBudget();
        totalAmountSpentTodayBudgetIndicator.setText(String.valueOf(
            "You're %s of your daily budget by %s".formatted(
                (dailyOverBudget <= 0) ? "under" : "over",
                Math.abs(dailyOverBudget)
            )
        ));

        // Set the color of the daily budget indicator
        if (dailyOverBudget >= 0) {
            totalAmountSpentTodayBudgetIndicator.setStyle("-fx-text-fill: #EE5D5D;");
        } else {
            totalAmountSpentTodayBudgetIndicator.setStyle("-fx-text-fill: #90DE5F;");
        }

        // --- daily expenses list ---

        // Set the daily expenses list
        List<Expense> dailyExpenses = App.expenses.getTodaysExpenses();
        dailyExpenses.forEach(expense -> {
            dailyExpensesList.getChildren().add(
                new Label(expense.toString())
            );
        });

    }
}
