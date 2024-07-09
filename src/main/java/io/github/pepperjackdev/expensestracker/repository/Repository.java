package io.github.pepperjackdev.expensestracker.repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import io.github.pepperjackdev.expensestracker.repository.expenses.Expenses;

/**
 * An interface that represents a Database (i.e. a data repository);
 * The generic parameter `T` is an object that represents a single database record.
 */
public sealed interface Repository<T> 
    permits Expenses {

    String getDatabaseStringPath();

    default Path getDatabasePath() {
        return Path.of(getDatabaseStringPath());
    }

    default boolean check() {
        // FIXME: Add a more rigorous checking method.
        return Files.exists(getDatabasePath());
    }

    void initialized();
    
}
