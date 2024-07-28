package io.github.pepperjackdev.expensestracker.controllers;

import io.github.pepperjackdev.expensestracker.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardViewDailyExpensesController {
    
    @FXML Label totalAmountSpentTodayBudgetIndicator;
    @FXML Label totalAmountSpentToday;

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
    }
}
