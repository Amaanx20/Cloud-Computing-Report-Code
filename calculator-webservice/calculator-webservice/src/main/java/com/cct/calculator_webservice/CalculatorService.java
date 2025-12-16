package com.cct.calculator_webservice;

import org.springframework.stereotype.Service;
/**
 * Service layer that contains the core calculator logic.
 * Each method performs a basic arithmetic operation and
 * is used by the CalculatorController.
 */
@Service
public class CalculatorService {

    /**
     * Adds two numbers and returns the result.
     */
    public double add(double number1, double number2) {
        return number1 + number2;
    }

    /**
     * Subtracts the second number from the first.
     */
    public double subtract(double number1, double number2) {
        return number1 - number2;
    }

    /**
     * Multiplies two numbers together.
     */
    public double multiply(double number1, double number2) {
        return number1 * number2;
    }

    /**
     * Divides the first number by the second.
     * Throws an exception if division by zero is attempted.
     */
    public double divide(double number1, double number2) {
        if (number2 == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed...");
        }
        return number1 / number2;
    }
}

