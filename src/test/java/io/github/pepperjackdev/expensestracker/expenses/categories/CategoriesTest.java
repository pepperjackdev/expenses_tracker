package io.github.pepperjackdev.expensestracker.expenses.categories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoriesTest {

    private Categories categories;

    @BeforeAll
    void initializeTestDatabase() {
        categories = new Categories("test_database.db");
    }

    @BeforeEach
    void setUp() {
        categories.deleteAllCategories();
        categories.addNewCategory("Food");
        categories.addNewCategory("Transportation");
        categories.addNewCategory("Clothing");
        categories.addNewCategory("Education");
        categories.addNewCategory("Health");
    }

    @Test
    void testAddNewCategory() {
        categories.addNewCategory("Entertainment");
        assertEquals(6, categories.getAllCategories().size());
    }

    @Test
    void testGetAllCategories() {
        assertEquals(5, categories.getAllCategories().size());
    }

    @Test
    void testDeleteCategory() {
        categories.deleteCategory(categories.getAllCategories().get(0)); // Removes the first category
        assertEquals(4, categories.getAllCategories().size());
    }
}
