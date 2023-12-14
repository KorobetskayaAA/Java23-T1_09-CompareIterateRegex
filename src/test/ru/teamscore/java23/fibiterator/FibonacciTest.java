package ru.teamscore.java23.fibiterator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FibonacciTest {
    @Test
    void testFirstNumbers() {
        String[] fibNumbers = { "1", "1", "2", "3", "5", "8", "13", "21", "34" };
        Fibonacci fib = new Fibonacci();
        for (String number : fibNumbers) {
            var nextFib = fib.iterator().next();
            assertEquals(number, nextFib.toString());
        }
    }

    @Test
    void testBigNumber() {
        int count = 100;
        String fibNumber = "354224848179261915075";
        Fibonacci fib = new Fibonacci();
        for (int i = 1; i < count && fib.iterator().hasNext(); i++) {
            fib.iterator().next();
        }
        assertEquals(fibNumber, fib.iterator().next().toString());
    }
}