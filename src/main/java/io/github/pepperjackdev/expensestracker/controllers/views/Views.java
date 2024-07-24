package io.github.pepperjackdev.expensestracker.controllers.views;

public enum Views {
    MAIN("main_view"),
    DASHBOARD("dashboard_view"),
    EXPENSES(null),
    BUDGET(null),
    STATISTICS(null),
    SETTINGS(null);

    private String fxml;

    private Views(String fxml) {
        this.fxml = fxml;
    }

    @Override
    public String toString() {
        return fxml;
    }
}
