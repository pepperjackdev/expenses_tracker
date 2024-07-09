module io.github.pepperjackdev.expensestracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens io.github.pepperjackdev.expensestracker to javafx.fxml;
    exports io.github.pepperjackdev.expensestracker;
}
