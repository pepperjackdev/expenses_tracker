package io.github.pepperjackdev.expensestracker.config.budget;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.pepperjackdev.expensestracker.utils.math.MathUtils;

public class BudgetTest {

    private Budget budget;

    @BeforeEach
    public void setUp() {
        budget = new Budget(Duration.ofDays(LocalDate.now().lengthOfYear()), 1000);
    }

    @Test
    public void testGetYearlyBudget() {
        double expected = 1000;
        double actual = budget.getYearlyBudget();
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void testSetYearlyBudget() {
        double expected = 1000;
        budget.setYearlyBudget(expected);
        double actual = budget.getYearlyBudget();
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void testGetMonthlyBudget() {
        double expected = MathUtils.roundByPlaces(1000.0 / LocalDate.now().lengthOfYear() * LocalDate.now().lengthOfMonth(), 2);
        double actual = budget.getMonthlyBudget();
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void testSetMonthlyBudget() {
        double expected = 1000;
        budget.setMonthlyBudget(expected);
        double actual = budget.getMonthlyBudget();
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void testGetWeeklyBudget() {
        double expected = MathUtils.roundByPlaces(1000.0 / LocalDate.now().lengthOfYear() * 7, 2);
        double actual = budget.getWeeklyBudget();
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void testSetWeeklyBudget() {
        double expected = 1000;
        budget.setWeeklyBudget(expected);
        double actual = budget.getWeeklyBudget();
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void testGetDailyBudget() {
        double expected = MathUtils.roundByPlaces(1000.0 / LocalDate.now().lengthOfYear(), 2);
        double actual = budget.getDailyBudget();
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void testSetDailyBudget() {
        double expected = 1000;
        budget.setDailyBudget(expected);
        double actual = budget.getDailyBudget();
        assertEquals(expected, actual, 0.0);
    }
}