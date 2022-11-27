package com.riccardorocco;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TwoVariablesTest {
    @Test
    public void testSwap() {
        TwoVariables test = new TwoVariables(5, 6);
        test.swap();
        assertEquals(6, test.getA());
        assertEquals(5, test.getB());
    }
}
