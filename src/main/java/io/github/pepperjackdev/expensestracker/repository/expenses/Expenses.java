package io.github.pepperjackdev.expensestracker.repository.expenses;

import static io.github.pepperjackdev.expensestracker.constans.Adresses.EXPENSES_DATABASE_PATH;

import java.time.LocalDate;

import io.github.pepperjackdev.expensestracker.repository.Repository;
import io.github.pepperjackdev.expensestracker.repository.expenses.categories.Category;

/**
 * Expenses classe: used to manage the expenses of the user
 * and store them in a database.
 */
public final class Expenses
    implements Repository<Expense> {

    public Expenses() {

    }

    // Implementing from Repository<...>
    @Override
    public String getDatabaseStringPath() {
        return EXPENSES_DATABASE_PATH; // Hardcoded
    }

    // Implementing from Repository<...>
    @Override
    public void initialized() {
        // FIXME: Give and implementation of this method
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

    public void remove() {
        
    }

}
