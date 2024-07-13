package io.github.pepperjackdev.expensestracker.expenses.categories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.github.pepperjackdev.expensestracker.database.Database;

public final class Categories
    extends Database<Category> {

    protected Categories(String path) {
        super(path);
    }

    @Override
    protected void initialize() {

        //
        // Initialize the categories table
        //

        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement initializeCategoriesTable = connection.prepareStatement("""
                CREATE TABLE IF NOT EXISTS categories (
                    id TEXT,
                    name TEXT
                )
            """);

            initializeCategoriesTable.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category addNewCategory(String name) {
        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            String id = UUID.randomUUID().toString();

            PreparedStatement addNewCategory = connection.prepareStatement("INSERT INTO categories (id, name) VALUES (?, ?)");
            addNewCategory.setString(1, id);
            addNewCategory.setString(2, name);
            addNewCategory.execute();

            return new Category(id, getConnectionString());

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Category> getAllCategories() {

        List<Category> categories = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement getCategories = connection.prepareStatement("SELECT * FROM categories");
            
            ResultSet resultSet = getCategories.executeQuery();
            while (resultSet.next()) {
                categories.add(new Category(resultSet.getString("id"), getConnectionString()));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    public void deleteCategory(Category category) {
        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement deleteCategory = connection.prepareStatement("DELETE FROM categories WHERE id=?");
            deleteCategory.setString(1, category.getId());
            deleteCategory.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllCategories() {
        try (Connection connection = DriverManager.getConnection(getConnectionString())) {
            PreparedStatement deleteAllCategories = connection.prepareStatement("DELETE FROM categories");
            deleteAllCategories.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
