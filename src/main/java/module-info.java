module io.github.pepperjackdev.expensestracker {

    // for JavaFx
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    // for SQL
    requires java.sql.rowset;

    opens io.github.pepperjackdev.expensestracker to javafx.fxml;
    exports io.github.pepperjackdev.expensestracker;
}
