package com.nmihalchenko.java.labs.lab7;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class ThreadGenerator {
    private final CountDownLatch latch;
    private final CalculatorThread[] threads;

    public ThreadGenerator(int operandCount) {
        if (operandCount <= 0) {
            throw new IllegalArgumentException("Operand count must be a positive integer");
        }

        this.latch = new CountDownLatch(operandCount);
        this.threads = IntStream.range(0, operandCount)
                .mapToObj(i -> {
                    String name = MessageFormat.format("Calculator thread #{0}", i + 1);
                    return new CalculatorThread(name, this.latch, 2 * (i + 1) + 1);
                })
                .toArray(CalculatorThread[]::new);
    }

    public void execute() {
        Arrays.stream(this.threads)
                .forEach(Thread::start);
    }

    public List<Integer> getResult() {
        try {
            this.latch.await();
        } catch (InterruptedException e) {
            Arrays.stream(this.threads)
                    .forEach(Thread::interrupt);
        }

        return Arrays.stream(this.threads)
                .map(CalculatorThread::getResult)
                .map(Optional::orElseThrow)
                .toList();
    }
}
