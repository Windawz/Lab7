package com.nmihalchenko.java.labs.lab7;

public class Runner {
    public static void main(String[] args) {
        int operandCount = Integer.parseInt(args[0]);

        ThreadGenerator generator = new ThreadGenerator(operandCount);

        generator.execute();

        OperationMenu menu = new OperationMenu(generator.getResult());

        menu.start();
    }
}
