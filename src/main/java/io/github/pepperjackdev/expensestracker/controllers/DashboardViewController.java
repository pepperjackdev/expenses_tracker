package io.github.pepperjackdev.expensestracker.controllers;

import io.github.pepperjackdev.expensestracker.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class DashboardViewController {
    @FXML BorderPane monthStatus;

    @FXML Label totalAmountSpentThisMonthBudgetIndicator;
    @FXML Label totalAmountSpentThisMonth;

    @FXML VBox dailyExpensesFrame;
    @FXML VBox budgetStatusFrame;

    @FXML
    void initialize() {

        // -----------------------------------------------------
        // Initializing shown data
        // -----------------------------------------------------

        // --- total amount spent this month ---

        // Set the total amount spent this month
        totalAmountSpentThisMonth.setText(String.valueOf(
            App.expenses.getTotalAmountOfThisMonthExpenses()
        ));

        // --- monthly budget indicator ---

        // Set the monthly budget indicator
        double monthlyOverBudget = App.expenses.getTotalAmountOfThisMonthExpenses() - App.config.getBudget().getMonthlyBudget();
        totalAmountSpentThisMonthBudgetIndicator.setText(String.valueOf(
            "You're %s of your monthly budget by %s".formatted(
                (monthlyOverBudget <= 0) ? "under" : "over",
                Math.abs(monthlyOverBudget)
            )
        ));

        // Set the color of the monthly budget indicator
        if (monthlyOverBudget >= 0) {
            totalAmountSpentThisMonthBudgetIndicator.setStyle("-fx-text-fill: #EE5D5D;");
        } else {
            totalAmountSpentThisMonthBudgetIndicator.setStyle("-fx-text-fill: #90DE5F;");
        }

        // -----------------------------------------------------
        // Initializing the boxes
        // -----------------------------------------------------

        dailyExpensesFrame.getChildren().setAll(
            App.loadFXML("dashboard_view_daily_expenses")
        );

        budgetStatusFrame.getChildren().setAll(
            App.loadFXML("dashboard_view_budget_status")
        );
    }
}
