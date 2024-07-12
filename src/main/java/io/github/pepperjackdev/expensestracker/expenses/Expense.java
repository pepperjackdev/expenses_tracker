package io.github.pepperjackdev.expensestracker.expenses;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Expense {

    private final String id;
    private final String connectionString;

    protected Expense(String id, String connectionString) {
        this.id = id;
        this.connectionString = connectionString;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            PreparedStatement getAmount = connection.prepareStatement("SELECT amount FROM expenses WHERE id=?");
            getAmount.setString(1, id);
            ResultSet resultSet = getAmount.executeQuery();
            return resultSet.getDouble("amount");
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public void setAmount(double amount) {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            PreparedStatement setAmount = connection.prepareStatement("UPDATE expenses SET amount=? WHERE id=?");
            setAmount.setDouble(1, amount);
            setAmount.setString(2, id);
            setAmount.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getDescription() {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            PreparedStatement getDescription = connection.prepareStatement("SELECT description FROM expenses WHERE id=?");
            getDescription.setString(1, id);
            ResultSet resultSet = getDescription.executeQuery();
            return resultSet.getString("description");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setDescription(String description) {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            PreparedStatement setDescription = connection.prepareStatement("UPDATE expenses SET description=? WHERE id=?");
            setDescription.setString(1, description);
            setDescription.setString(2, id);
            setDescription.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LocalDate getDate() {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            PreparedStatement getDate = connection.prepareStatement("SELECT date FROM expenses WHERE id=?");
            getDate.setString(1, id);
            ResultSet resultSet = getDate.executeQuery();
            
            LocalDate date = resultSet.getDate("date").toLocalDate();
            return date;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setDate(LocalDate date) {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            PreparedStatement setDate = connection.prepareStatement("UPDATE expenses SET date=? WHERE id=?");
            setDate.setDate(1, Date.valueOf(date));
            setDate.setString(2, id);
            setDate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getCategory() {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            PreparedStatement getCategory = connection.prepareStatement("SELECT category FROM expenses WHERE id=?");
            getCategory.setString(1, id);
            ResultSet resultSet = getCategory.executeQuery();
            return resultSet.getString("category");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setCategory(String category) {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            PreparedStatement setCategory = connection.prepareStatement("UPDATE expenses SET category=? WHERE id=?");
            setCategory.setString(1, category);
            setCategory.setString(2, id);
            setCategory.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Expense<%s> (amount: %f, description: %s, date: %s, category: %s)".formatted(id, getAmount(), getDescription(), getDate(), getCategory());
    }
}
