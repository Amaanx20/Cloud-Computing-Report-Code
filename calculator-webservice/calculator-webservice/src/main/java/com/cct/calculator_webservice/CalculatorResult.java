package com.cct.calculator_webservice;
/**
 * Simple record used to return calculator results as JSON.
 * Contains the operation name, the two inputs, and the final result.
 */
public record CalculatorResult(
        String operation, // e.g. "add", "subtract"
        double number1,   // first input
        double number2,   // second input
        double result     // calculated output
) {}
