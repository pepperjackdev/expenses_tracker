package io.github.pepperjackdev.expensestracker.expenses.categories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Category {
    
    private final String id;
    private final String connectionString;

    protected Category(String id, String connectionString) {
        this.id = id;
        this.connectionString = connectionString;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            PreparedStatement getCategoryName = connection.prepareStatement("SELECT name FROM categories WHERE id = ?");
            getCategoryName.setString(1, id);

            ResultSet resultSet = getCategoryName.executeQuery();
            resultSet.next();

            return resultSet.getString("name");

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setName(String name) {
        try (Connection connection = DriverManager.getConnection(connectionString)) {
            PreparedStatement setCategoryName = connection.prepareStatement("UPDATE categories SET name = ? WHERE id = ?");
            setCategoryName.setString(1, name);
            setCategoryName.setString(2, id);
            setCategoryName.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
