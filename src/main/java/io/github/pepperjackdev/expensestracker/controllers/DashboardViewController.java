package io.github.pepperjackdev.expensestracker.controllers;

import io.github.pepperjackdev.expensestracker.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardViewController {
    
    @FXML Label totalAmountSpentThisMonthBudgetIndicator;
    @FXML Label totalAmountSpentThisMonth;

    @FXML
    void initialize() {

        // -----------------------------------------------------
        // Initializing shown data
        // -----------------------------------------------------

        totalAmountSpentThisMonth.setText(String.valueOf(
            App.expenses.getTotalAmountOfExpensesOfThisMonth()
        ));

        totalAmountSpentThisMonthBudgetIndicator.setText(String.valueOf(
            "You're %s of your monthly budget by %s".formatted(
                (App.config.getBudget().getMonthlyBudget() > App.expenses.getTotalAmountOfExpensesOfThisMonth()) ? "under" : "over",
                App.expenses.getTotalAmountOfExpensesOfThisMonth() - App.config.getBudget().getMonthlyBudget()
            )
        ));
    }
}
