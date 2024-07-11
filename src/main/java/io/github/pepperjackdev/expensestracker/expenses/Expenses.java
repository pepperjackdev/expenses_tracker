package io.github.pepperjackdev.expensestracker.expenses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.pepperjackdev.expensestracker.database.Database;

public class Expenses 
    extends Database<Expense>{

    protected Expenses(String path) {
        super(path);
    }

    @Override
    protected void initialize() {
        // initializing expenses table
        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            connection.createStatement()
                .execute("CREATE TABLE IF NOT EXISTS expenses (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, amount REAL, date TEXT, category_id TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
