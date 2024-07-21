module io.github.pepperjackdev.expensestracker {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    requires java.sql.rowset;

    opens io.github.pepperjackdev.expensestracker;
    opens io.github.pepperjackdev.expensestracker.expenses;
    opens io.github.pepperjackdev.expensestracker.controllers;
    
    exports io.github.pepperjackdev.expensestracker;
    exports io.github.pepperjackdev.expensestracker.expenses;
    exports io.github.pepperjackdev.expensestracker.controllers;

}
