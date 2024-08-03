package io.github.pepperjackdev.expensestracker.config.budget;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Period;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BudgetTest {

    private Budget budget;

    @BeforeEach
    public void setUp() {
        Period period = Period.ofYears(1).withMonths(12);
        double amount = 12000.0;
        budget = new Budget(period, amount);
    }

    @Test
    public void testGetPeriod() {
        Period expectedPeriod = Period.ofYears(1).withMonths(12);
        assertEquals(expectedPeriod, budget.getPeriod());
    }

    @Test
    public void testSetPeriod() {
        Period newPeriod = Period.ofYears(2).withMonths(24);
        budget.setPeriod(newPeriod);
        assertEquals(newPeriod, budget.getPeriod());
    }

    @Test
    public void testGetYearlyBudget() {
        double expectedYearlyBudget = 12000.0;
        assertEquals(expectedYearlyBudget, budget.getYearlyBudget(), 0.0);
    }

    @Test
    public void testSetYearlyBudget() {
        double newYearlyBudget = 24000.0;
        budget.setYearlyBudget(newYearlyBudget);
        assertEquals(newYearlyBudget, budget.getYearlyBudget(), 0.0);
    }

    @Test
    public void testGetMonthlyBudget() {
        double expectedMonthlyBudget = 1000.0;
        assertEquals(expectedMonthlyBudget, budget.getMonthlyBudget(), 0.0);
    }

    @Test
    public void testSetMonthlyBudget() {
        double newMonthlyBudget = 2000.0;
        budget.setMonthlyBudget(newMonthlyBudget);
        assertEquals(newMonthlyBudget, budget.getMonthlyBudget(), 0.0);
    }

    @Test
    public void testGetWeeklyBudget() {
        double expectedWeeklyBudget = 230.76923076923077;
        assertEquals(expectedWeeklyBudget, budget.getWeeklyBudget(), 0.0);
    }

    @Test
    public void testSetWeeklyBudget() {
        double newWeeklyBudget = 461.53846153846155;
        budget.setWeeklyBudget(newWeeklyBudget);
        assertEquals(newWeeklyBudget, budget.getWeeklyBudget(), 0.0);
    }

    @Test
    public void testGetDailyBudget() {
        double expectedDailyBudget = 3.896103896103896;
        assertEquals(expectedDailyBudget, budget.getDailyBudget(), 0.0);
    }

    @Test
    public void testSetDailyBudget() {
        double newDailyBudget = 6.56;
        budget.setDailyBudget(newDailyBudget);
        assertEquals(newDailyBudget, budget.getDailyBudget(), 0.0);
    }
}