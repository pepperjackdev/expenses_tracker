package io.github.pepperjackdev.expensestracker.utils.currencies;

public enum Currency {

    USD("$", "United States Dollar"),
    EUR("€", "Euro");

    private final String currencySymbol;
    private final String currencyName;

    Currency(String currencySymbol, String currencyName) {
        this.currencySymbol = currencySymbol;
        this.currencyName = currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public String getCurrencyName() {
        return currencyName;
    }
}
