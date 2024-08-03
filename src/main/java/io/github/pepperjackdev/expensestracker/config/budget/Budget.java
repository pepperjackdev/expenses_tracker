package io.github.pepperjackdev.expensestracker.config.budget;

import java.io.Serializable;
import java.time.Period;
import java.time.temporal.TemporalUnit;

import io.github.pepperjackdev.expensestracker.utils.math.Calculus;

public class Budget
    implements Serializable{

    private Period period;
    private double amount;
    
    public Budget(Period period, double amount) {
        this.period = period;
        this.amount = amount;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public double getYearlyBudget() {
        return Calculus.roundByPlaces(amount / period.getYears(), 2);
    }

    public void setYearlyBudget(double yearlyBudget) {
        setPeriod(Period.ofYears(1));
        amount = yearlyBudget;
    }

    public double getMonthlyBudget() {
        return Calculus.roundByPlaces(amount / period., 2);
    }

    public void setMonthlyBudget(double monthlyBudget) {
        setPeriod(Period.ofMonths(1));
        amount = monthlyBudget;
    }

    public double getWeeklyBudget() {
        return Calculus.roundByPlaces(amount / (period.getDays() * 7), 2);
    }

    public void setWeeklyBudget(double weeklyBudget) {
        setPeriod(Period.ofWeeks(1));
        amount = weeklyBudget;
    }

    public double getDailyBudget() {
        return Calculus.roundByPlaces(amount / period.getDays(), 2);
    }

    public void setDailyBudget(double dailyBudget) {
        setPeriod(Period.ofDays(1));
        amount = dailyBudget;
    }

    @Override
    public String toString() {
        return "Budget of %s for %s years, %s months, %s days".formatted(amount, period.getYears(), period.getMonths(), period.getDays());
    }
}