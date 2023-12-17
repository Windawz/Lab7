package com.nmihalchenko.java.labs.lab7;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OperationMenu {
    private static final List<Operation> OPERATIONS = Arrays.asList(
            Operation.ADDITION,
            Operation.SUBTRACTION,
            Operation.MULTIPLICATION);

    private final List<Integer> results;
    private final Scanner scanner;

    public OperationMenu(List<Integer> results) {
        if (results == null) {
            throw new IllegalArgumentException("Results may not be null");
        }

        this.results = results;

        this.scanner = new Scanner(System.in);
    }

    public void start() {
        draw();
        int input = getInput();
        executeAndPrint(input);
    }

    private void draw() {
        String resultsString = this.results.stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        String message = MessageFormat.format("Choose reduction operation over elements [{0}]:", resultsString);
        System.out.println(message);
        for (int i = 0; i < OPERATIONS.size(); i++) {
            System.out.println(MessageFormat.format(
                    "  {0}. {1}",
                    i + 1,
                    OPERATIONS.get(i).getSymbol()));
        }
    }

    private int getInput() {
        boolean isValid = false;
        int input = 0;

        while (!isValid) {
            try {
                input = this.scanner.nextInt();
                isValid = input > 0 && input <= OPERATIONS.size();
            } catch (InputMismatchException e) {
                scanner.next();
            }

            if (!isValid) {
                System.out.println("Invalid operation number. Please, try again.");
            }
        }

        return input - 1;
    }

    private void executeAndPrint(int input) {
        var operation = OPERATIONS.get(input);

        int result = this.results.stream()
                .reduce(operation)
                .orElse(0);

        System.out.println(MessageFormat.format(
                "Result of operation {0} is: {1}",
                operation.getSymbol(),
                result));
    }
}
