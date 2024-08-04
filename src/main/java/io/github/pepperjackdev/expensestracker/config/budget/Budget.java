package io.github.pepperjackdev.expensestracker.config.budget;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.YearMonth;

import io.github.pepperjackdev.expensestracker.utils.math.MathUtils;

public class Budget
    implements Serializable{

    private Duration period;
    private double amount;
    
    public Budget(Duration period, double amount) {
        this.period = period;
        this.amount = amount;
    }

    private void setPeriod(Duration period) {
        this.period = period;
    }

    public double getYearlyBudget() {
        return MathUtils.roundByPlaces(amount / period.toDays() * LocalDate.now().lengthOfYear(),  2);
    }

    public void setYearlyBudget(double yearlyBudget) {
        setPeriod(Duration.ofDays(LocalDate.now().lengthOfYear()));
        amount = yearlyBudget;
    }

    public double getMonthlyBudget() {
        return MathUtils.roundByPlaces(amount / period.toDays() * YearMonth.now().lengthOfMonth(), 2);
    }

    public void setMonthlyBudget(double monthlyBudget) {
        setPeriod(Duration.ofDays(YearMonth.now().lengthOfMonth()));
        amount = monthlyBudget;
    }

    public double getWeeklyBudget() {
        return MathUtils.roundByPlaces(amount / period.toDays() * 7, 2);
    }

    public void setWeeklyBudget(double weeklyBudget) {
        setPeriod(Duration.ofDays(7));
        amount = weeklyBudget;
    }

    public double getDailyBudget() {
        return MathUtils.roundByPlaces(amount / period.toDays(), 2);
    }

    public void setDailyBudget(double dailyBudget) {
        setPeriod(Duration.ofDays(1));
        amount = dailyBudget;
    }
}