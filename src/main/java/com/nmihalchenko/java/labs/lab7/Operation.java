package com.nmihalchenko.java.labs.lab7;

import java.util.function.BinaryOperator;

public class Operation implements BinaryOperator<Integer> {
    public static Operation ADDITION = new Operation("+", Integer::sum);
    public static Operation SUBTRACTION = new Operation("-", (l, r) -> l - r);
    public static Operation MULTIPLICATION = new Operation("*", (l, r) -> l * r);
    private final String symbol;
    private final BinaryOperator<Integer> actualOperation;

    private Operation(String symbol, BinaryOperator<Integer> actualOperation) {
        if (symbol == null || symbol.isBlank()) {
            throw new IllegalArgumentException("Symbol may not be null or blank");
        }

        if (actualOperation == null) {
            throw new IllegalArgumentException("Actual operation may not be null");
        }

        this.symbol = symbol;
        this.actualOperation = actualOperation;
    }

    @Override
    public Integer apply(Integer integer, Integer integer2) {
        return actualOperation.apply(integer, integer2);
    }

    public String getSymbol() {
        return this.symbol;
    }
}
