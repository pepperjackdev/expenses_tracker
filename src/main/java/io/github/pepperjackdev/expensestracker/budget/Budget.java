package io.github.pepperjackdev.expensestracker.budget;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import io.github.pepperjackdev.expensestracker.database.Database;

public final class Budget 
    extends Database {

    protected Budget(String path) {
        super(path);
    }

    @Override
    protected boolean check() {
        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement checkExpensesTable = connection.prepareStatement("SELECT * FROM budget_config");
            return checkExpensesTable.execute();
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    protected void initialize() {
        try (Connection connection = DriverManager.getConnection(getConnectionString())) {

            //
            // Initialize the budget table
            //

            PreparedStatement initializeBudgetConfigTable = connection.prepareStatement("""
                CREATE TABLE IF NOT EXISTS budget_config (
                	property TEXT,
                    value TEXT
                )
            """);

            initializeBudgetConfigTable.execute();

            // Let's add the config records

            PreparedStatement addDailyBudgetField = connection.prepareStatement("INSERT INTO budget_config ('property', 'value') VALUES (?, null)");
            addDailyBudgetField.setString(1, "daily_budget");
            addDailyBudgetField.execute();

            PreparedStatement addMonthlyBudgetField = connection.prepareStatement("INSERT INTO budget_config ('property', 'value') VALUES (?, null)");
            addMonthlyBudgetField.setString(1, "monthly_budget");
            addMonthlyBudgetField.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setDailyBudget(float dailyBudget) {
        // Let's update the daily budget
        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement setDailyBudget = connection.prepareStatement("UPDATE budget_config SET value = ? WHERE property = 'daily_budget'");
            setDailyBudget.setFloat(1, dailyBudget);
            setDailyBudget.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public float getDailyBudget() {
        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement getDailyBudget = connection.prepareStatement("SELECT value FROM budget_config WHERE property = 'daily_budget'");
            return getDailyBudget.executeQuery().getFloat(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void setMonthlyBudget(float monthlyBudget) {
        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement setMonthlyBudget = connection.prepareStatement("UPDATE budget_config SET value = ? WHERE property = 'monthly_budget'");
            setMonthlyBudget.setFloat(1, monthlyBudget);
            setMonthlyBudget.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public float getMonthlyBudget() {
        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement getMonthlyBudget = connection.prepareStatement("SELECT value FROM budget_config WHERE property = 'monthly_budget'");
            return getMonthlyBudget.executeQuery().getFloat(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
    
}
