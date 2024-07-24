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
    }
    
}
