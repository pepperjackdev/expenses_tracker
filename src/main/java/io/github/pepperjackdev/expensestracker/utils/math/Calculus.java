package io.github.pepperjackdev.expensestracker.utils.math;

public class Calculus {
    public static double roundByPlaces(double value, int places) {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }
}
