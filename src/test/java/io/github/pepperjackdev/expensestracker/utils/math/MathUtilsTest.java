package io.github.pepperjackdev.expensestracker.utils.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MathUtilsTest {
    
    @Test
    public void testRoundByPlaces() {
        double number = 1.23456789;
        int places = 2;
        double expected = 1.23;
        double actual = MathUtils.roundByPlaces(number, places);
        assertEquals(expected, actual, 0.0);
    }
}
