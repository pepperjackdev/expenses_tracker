package io.github.pepperjackdev.expensestracker.config.budget;

import java.io.Serializable;
import java.time.Period;

public class Budget
    implements Serializable{

    private static final double DEFAULT_AMOUNT = 0.0;
    private static final Period DEFAULT_PERIOD = Period.ofYears(0);

    private Period period;
    private double amount;
    
    public Budget(Period period, double amount) {
        this.period = period;
        this.amount = amount;
    }

    public Budget() { // FIXME: This is not a good idea.
        this(DEFAULT_PERIOD, DEFAULT_AMOUNT);
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public double getYearlyBudget() {
        return amount / period.getYears();
    }

    public void setYearlyBudget(double yearlyBudget) {
        amount = yearlyBudget * period.getYears();
    }

    public double getMonthlyBudget() {
        return amount / period.getMonths();
    }

    public void setMonthlyBudget(double monthlyBudget) {
        amount = monthlyBudget * period.getMonths();
    }

    public double getWeeklyBudget() {
        return amount / period.getDays() / 7;
    }

    public void setWeeklyBudget(double weeklyBudget) {
        amount = weeklyBudget * period.getDays() * 7;
    }

    public double getDailyBudget() {
        return amount / period.getDays();
    }

    public void setDailyBudget(double dailyBudget) {
        amount = dailyBudget * period.getDays();
    }
}