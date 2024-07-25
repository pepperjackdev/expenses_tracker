package io.github.pepperjackdev.expensestracker.expenses;

import java.lang.classfile.constantpool.PoolEntry;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import io.github.pepperjackdev.expensestracker.database.Database;

public final class Expenses
    extends Database {

    public Expenses(String path) {
        super(path);
    }

    protected boolean check() {
        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement checkExpensesTable = connection.prepareStatement("SELECT * FROM expenses");
            return checkExpensesTable.execute();
        } catch (SQLException e) {
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

            // Input validations // FIXME: Validate ALL the fields
            Objects.requireNonNull(date);

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

    public List<Expense> getTodaysExpenses() {
        return getAllExpensesOfDateRange(LocalDate.now(), LocalDate.now());
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

    public double getTotalAmountOfExpensesOfDateRange(LocalDate from, LocalDate to) {

        double amount = -1;

        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement getTotalAmountOfExpensesOfDateRange = connection.prepareStatement("SELECT SUM(amount) AS total_expenses FROM expenses WHERE date BETWEEN ? AND ?");
            getTotalAmountOfExpensesOfDateRange.setDate(1, Date.valueOf(from));
            getTotalAmountOfExpensesOfDateRange.setDate(2, Date.valueOf(to));
            getTotalAmountOfExpensesOfDateRange.execute();

            amount = getTotalAmountOfExpensesOfDateRange.getResultSet().getDouble("total_expenses");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return amount;
    }

    public double getTotalAmountOfExpensesOfThisMonth() {
        YearMonth thisMonth = YearMonth.of(LocalDate.now().getYear(), LocalDate.now().getMonth().getValue());
        LocalDate monthStart = thisMonth.atDay(1);
        LocalDate monthEnd = thisMonth.atEndOfMonth();
        return getTotalAmountOfExpensesOfDateRange(monthStart, monthEnd);
    }

    public double getTotalAmountOfExpensesOfToday() {
        return getTotalAmountOfExpensesOfDateRange(LocalDate.now(), LocalDate.now());
    }

    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement getAllCategories = connection.prepareStatement("SELECT DISTINCT category FROM expenses");
            ResultSet resultSet = getAllCategories.executeQuery();

            while (resultSet.next()) {
                categories.add(resultSet.getString("category"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    public List<String> getMostTargettedCategories() {
        List<String> categories = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement getMostTargettedCategories = connection.prepareStatement("SELECT category, COUNT(category) AS count FROM expenses GROUP BY category ORDER BY count DESC");
            ResultSet resultSet = getMostTargettedCategories.executeQuery();

            while (resultSet.next()) {
                categories.add(resultSet.getString("category"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
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
