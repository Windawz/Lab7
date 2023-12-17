package com.nmihalchenko.java.labs.lab7;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;

public class CalculatorThread extends Thread {
    private final CountDownLatch latch;
    private final int operandValue;
    private Integer result = null;

    public CalculatorThread(String threadName, CountDownLatch latch, int operandValue) {
        super(threadName);

        if (latch == null) {
            throw new IllegalArgumentException("Latch may not be null");
        }

        if (operandValue < 0) {
            throw new IllegalArgumentException("Operand value may not be negative");
        }

        this.latch = latch;
        this.operandValue = operandValue;
    }

    private static int calculateDoubleFactorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        int accumulator = n;
        for (int multiplier = n - 2; multiplier > 0; multiplier--) {
            accumulator *= multiplier;
        }
        return accumulator;
    }

    @Override
    public void run() {
        this.result = calculateDoubleFactorial(this.operandValue);
        latch.countDown();
    }

    public Optional<Integer> getResult() {
        return Optional.ofNullable(this.result);
    }
}
