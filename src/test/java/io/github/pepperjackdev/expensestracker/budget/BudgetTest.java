package io.github.pepperjackdev.expensestracker.budget;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BudgetTest {

    private Budget budget;

    @BeforeAll
    public void setUp() throws Exception {
        budget = new Budget("test.db");
    }

    @Test
    public void testCheck() {
        assertTrue(budget.check());
    }

    @Test
    public void testSetAndGetDailyBudget() {
        float dailyBudget = 50.0f;
        budget.setDailyBudget(dailyBudget);
        assertEquals(dailyBudget, budget.getDailyBudget(), 0.0);
    }

    @Test
    public void testSetAndGetMonthlyBudget() {
        float monthlyBudget = 1500.0f;
        budget.setMonthlyBudget(monthlyBudget);
        assertEquals(monthlyBudget, budget.getMonthlyBudget(), 0.0);
    }
}