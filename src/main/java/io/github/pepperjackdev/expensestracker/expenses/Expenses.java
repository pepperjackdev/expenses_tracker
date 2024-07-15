package io.github.pepperjackdev.expensestracker.expenses;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.github.pepperjackdev.expensestracker.database.Database;

public final class Expenses
    extends Database<Expense> {

    public Expenses(String path) {
        super(path);
    }

    protected boolean check() {
        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement checkExpensesTable = connection.prepareStatement("SELECT * FROM expenses");
            checkExpensesTable.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void initialize() {
        try (Connection connection = DriverManager.getConnection(getConnectionString())) {

            //
            // Initialize the expenses table
            //

            PreparedStatement initializeExpensesTable = connection.prepareStatement("""
                CREATE TABLE IF NOT EXISTS expenses (
                	id TEXT, 
                	amount REAL,
                	description TEXT,
                	date DATE,
                	category TEXT
                )
            """);

            initializeExpensesTable.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Expense addNewExpense(
        double amount,
        String description,
        LocalDate date,
        String category) {
            try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            String id = UUID.randomUUID().toString();

            PreparedStatement addNewExpense = connection.prepareStatement("INSERT INTO expenses (id, amount, description, date, category) VALUES (?, ?, ?, ?, ?)");
            addNewExpense.setString(1, id);
            addNewExpense.setDouble(2, amount);
            addNewExpense.setString(3, description);
            addNewExpense.setDate(4, Date.valueOf(date));
            addNewExpense.setString(5, category);
            addNewExpense.execute();

            return new Expense(id, getConnectionString());

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
}

    public List<Expense> getAllExpenses() {
        List<Expense> expenses = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement getAllExpenses = connection.prepareStatement("SELECT id FROM expenses");
            ResultSet resultSet = getAllExpenses.executeQuery();

            while (resultSet.next()) {
                expenses.add(new Expense(
                    resultSet.getString("id"),
                    getConnectionString()
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expenses;
    }

    public List<Expense> getAllExpensesOfCategory(String category) {
        List<Expense> expenses = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement getAllExpenses = connection.prepareStatement("SELECT id FROM expenses WHERE category=?");
            getAllExpenses.setString(1, category);
            ResultSet resultSet = getAllExpenses.executeQuery();

            while (resultSet.next()) {
                expenses.add(new Expense(
                    resultSet.getString("id"),
                    getConnectionString()
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expenses;
    }

    public List<Expense> getAllExpensesOfDateRange(LocalDate from, LocalDate to) {
        List<Expense> expenses = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement getAllExpenses = connection.prepareStatement("SELECT id FROM expenses WHERE date BETWEEN ? AND ?");
            getAllExpenses.setString(1, from.toString());
            getAllExpenses.setString(2, to.toString());
            ResultSet resultSet = getAllExpenses.executeQuery();

            while (resultSet.next()) {
                expenses.add(new Expense(
                    resultSet.getString("id"),
                    getConnectionString()
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expenses;
    }

    public List<Expense> getAllExpensesOfCategoryAndDateRange(String category, LocalDate from, LocalDate to) {
        List<Expense> expenses = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement getAllExpenses = connection.prepareStatement("SELECT id FROM expenses WHERE category=? AND date BETWEEN ? AND ?");
            getAllExpenses.setString(1, category);
            getAllExpenses.setDate(2, Date.valueOf(from));
            getAllExpenses.setDate(3, Date.valueOf(to));
            ResultSet resultSet = getAllExpenses.executeQuery();

            while (resultSet.next()) {
                expenses.add(new Expense(
                    resultSet.getString("id"),
                    getConnectionString()
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expenses;
    }

    public void deleteExpense(Expense expense) {
        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement deleteExpense = connection.prepareStatement("DELETE FROM expenses WHERE id=?");
            deleteExpense.setString(1, expense.getId());
            deleteExpense.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllExpenses() {
        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement deleteAllExpenses = connection.prepareStatement("DELETE FROM expenses");
            deleteAllExpenses.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
