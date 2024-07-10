package io.github.pepperjackdev.expensestracker.repository;

import java.nio.file.Files;
import java.nio.file.Path;

import io.github.pepperjackdev.expensestracker.repository.expenses.Expenses;

/**
 * An abstract class that represents a Database (i.e. a data repository);
 * The generic parameter `T` is an object that represents a single database record.
 */
public sealed abstract class Repository<T> 
    permits Expenses {

    protected final String databaseStringPath;
    protected final String connectionString;

    /**
     * Constructor for the Repository class.
     */
    protected Repository() {
        this.databaseStringPath = getDatabaseStringPath();
        this.connectionString = "jdbc:sqlite:%s".formatted(this.databaseStringPath);

        if (!check()) { // if the database does not exist
            initialize();
        }
    }

    /**
     * Constructor for the Repository class.
     * @param databaseStringPath The relative path of the repository database as a `java.lang.String` object (e.g. 'local/database.db',
     *                           where 'local' is a directory under the project root).
     */
    protected Repository(String databaseStringPath) {
        this.databaseStringPath = databaseStringPath;
        this.connectionString = "jdbc:sqlite:%s".formatted(databaseStringPath);

        if (!check()) { // if the database does not exist
            initialize();
        }
    }

    /**
     * 
     * @return The relative path of the repository database as a `java.lang.String` object (e.g. 'local/database.db',
     *         where 'local' is a directory under the project root).
     * 
     * @see getDatabasePath()
     */
    protected String getDatabaseStringPath() {
        return databaseStringPath;
    };

    /**
     * 
     * @return The repository database path as `java.nio.file.Path` object.
     *         It is equal to `Path.of(getDatabaseStringPath())`.
     * 
     * @see getDatabaseStringPath()
     */
    protected Path getDatabasePath() {
        return Path.of(getDatabaseStringPath());
    }

    /**
     * Checks whether the repository database is present or not.
     * @return true if the database at path `getDatabasePath` exists, false otherwise.
     */
    protected boolean check() {
        // FIXME: Add a more rigorous checking method.
        return Files.exists(getDatabasePath());
    }

    /**
     * Initializes the repository database with the necessary tables.
     */
    protected abstract void initialize();

}
