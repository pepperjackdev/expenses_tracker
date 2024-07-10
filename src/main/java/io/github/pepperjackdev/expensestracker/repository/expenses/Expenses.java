package io.github.pepperjackdev.expensestracker.repository.expenses;

import static io.github.pepperjackdev.expensestracker.constans.repository.Repository.CATEGORIES_TABLE;
import static io.github.pepperjackdev.expensestracker.constans.repository.Repository.EXPENSES_TABLE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import io.github.pepperjackdev.expensestracker.repository.Repository;
import io.github.pepperjackdev.expensestracker.repository.expenses.categories.Category;

/**
 * Expenses class: used to manage the expenses of the user
 * and store them in a database.
 */
public final class Expenses
    extends Repository<Expense> {
    
    public Expenses() {
        
    }

    // Implementing from Repository<...>
    @Override
    protected void initialize() {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            
            //
            // CREATING EXPENSES TABLE
            //
            
            PreparedStatement createExpensesTable = connection.prepareStatement("""
                CREATE TABLE IF NOT EXISTS %s (
                    id INTEGER PRIMARY KEY,
                    title TEXT NOT NULL,
                    description TEXT,
                    amount REAL NOT NULL,
                    category_id INTEGER,
                    date TEXT NOT NULL
                );
            """.formatted(EXPENSES_TABLE));
            createExpensesTable.executeUpdate();

            //
            // CREATING CATEGORIES TABLE
            //

            PreparedStatement createCategoriesTable = connection.prepareStatement("""
                CREATE TABLE IF NOT EXISTS %s (
                    id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL
                );
            """.formatted(CATEGORIES_TABLE));
            createCategoriesTable.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add

    public void add(String title, String description, double amount, Category category, LocalDate date) {
        
    }

    public void add(String title, String description, double amount, Category category) {
        LocalDate today = LocalDate.now();
        add(title, description, amount, category, today);
    }

    public void add(String title, String description, double amount, LocalDate date) {
        add(title, description, amount, null, date);
    }

    public void add(String title, String description, double amount) {
        LocalDate today = LocalDate.now();
        add(title, description, amount, null, today);
    }

    // Remove

    public void remove(Expense expense) {
        
    }

}
