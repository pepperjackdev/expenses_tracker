package io.github.pepperjackdev.expensestracker.database;

import java.nio.file.Path;

import io.github.pepperjackdev.expensestracker.budget.Budget;
import io.github.pepperjackdev.expensestracker.expenses.Expenses;

/**
 * Database
 */
public sealed abstract class Database
    permits Expenses, Budget {
    
    protected final String databaseStringPath; // The path to the database file as a String
    private final String connectionString; // The connection string to the database

    /**
     * Constructor
     * 
     * @param path The path to the database file
     */
    protected Database(String path) {
        this.databaseStringPath = path;
        this.connectionString = "jdbc:sqlite:" + path;

        if (!this.check()) {
            this.initialize();
        }
    }

    /**
     * Gets the database path
     * 
     * @return The path to the database file as a String (java.lang.String)
     */
    protected String getDatabaseStringPath() {
        return this.databaseStringPath;
    }

    /**
     * Gets the database path
     * 
     * @return The path to the database file as a Path (java.nio.file.Path)
     */
    protected Path getDatabasePath() {
        return Path.of(this.databaseStringPath);
    }

    /**
     * Gets the connection string
     * 
     * @return The connection string
     */
    protected String getConnectionString() {
        return this.connectionString;
    }

    /**
     * Checks if the database file exists
     * 
     * @return True if the database file exists, false otherwise
     */
    protected abstract boolean check();

    /**
     * Initializes the database, creating the database file if it does not exist
     */
    protected abstract void initialize();

}
