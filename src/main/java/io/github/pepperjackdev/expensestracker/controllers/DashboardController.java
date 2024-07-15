package io.github.pepperjackdev.expensestracker.controllers;

import static io.github.pepperjackdev.expensestracker.App.expenses;

import java.util.Arrays;

import io.github.pepperjackdev.expensestracker.utils.currencies.Currency;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

public class DashboardController {
    
    @FXML Label totalMonthAmount;
    @FXML ChoiceBox<String> currencyChoise;

    public void initialize() {
        totalMonthAmount.setText(String.valueOf(expenses.getAllExpenses().stream()
                .mapToDouble(expense -> expense.getAmount())
                .sum()));

        currencyChoise.getItems().addAll(Arrays.stream(Currency.values())
                .map(currency -> currency.getCurrencySymbol())
                .toArray(String[]::new));
    }
}
