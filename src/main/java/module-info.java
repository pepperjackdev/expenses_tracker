module io.github.pepperjackdev.expensestracker {
    // requires javafx.controls;
    // requires javafx.fxml;
    // requires transitive javafx.graphics;

    requires java.sql;

    // opens io.github.pepperjackdev.expensestracker to javafx.fxml;
    exports io.github.pepperjackdev.expensestracker;
    exports io.github.pepperjackdev.expensestracker.expenses;
    
    opens io.github.pepperjackdev.expensestracker; 
    opens io.github.pepperjackdev.expensestracker.expenses;
}
