package io.github.pepperjackdev.expensestracker.controllers;

import io.github.pepperjackdev.expensestracker.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DashboardViewBudgetStatusController {
    
    @FXML Label monthlyBudget;
    @FXML Label dailyBudget;
    @FXML Label remainingBudget;
    @FXML Label toRecover;

    @FXML
    void initialize() {
        monthlyBudget.setText("%.2f".formatted(App.config.getBudget().getMonthlyBudget()));
        dailyBudget.setText("%.2f".formatted(App.config.getBudget().getDailyBudget()));

        double remainingDailyBudget = App.config.getBudget().getDailyBudget() - App.expenses.getTotalAmountOfTodayExpenses();

        if (remainingDailyBudget < 0) {
            remainingBudget.setText("0");
            toRecover.setStyle("-fx-text-fill: #EE5D5D;");
        } else {
            remainingBudget.setText("%.2f".formatted(remainingDailyBudget));
            toRecover.setStyle("-fx-text-fill: white;");
        }

        toRecover.setText("Coming soon!");
    }
}
