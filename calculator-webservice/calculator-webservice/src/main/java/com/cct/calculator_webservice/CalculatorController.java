package com.cct.calculator_webservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller that exposes calculator operations as REST endpoints.
 * Each method handles a specific operation and returns a JSON response.
 */
@RestController
@RequestMapping("/calculator")
public class CalculatorController {

    private final CalculatorService calculatorService;

    /**
     * Injects the CalculatorService so the controller
     * can call the calculation methods.
     */
    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    /**
     * Adds two numbers.
     * Example: /calculator/add?number1=5&number2=3.
     */
    @GetMapping("/add")
    public ResponseEntity<CalculatorResult> add(
            @RequestParam double number1,
            @RequestParam double number2) {

        double result = calculatorService.add(number1, number2);
        return ResponseEntity.ok(
                new CalculatorResult("add", number1, number2, result)
        );
    }

    /**
     * Subtracts the second number from the first.
     */
    @GetMapping("/subtract")
    public ResponseEntity<CalculatorResult> subtract(
            @RequestParam double number1,
            @RequestParam double number2) {

        double result = calculatorService.subtract(number1, number2);
        return ResponseEntity.ok(
                new CalculatorResult("subtract", number1, number2, result)
        );
    }

    /**
     * Multiplies two numbers.
     */
    @GetMapping("/multiply")
    public ResponseEntity<CalculatorResult> multiply(
            @RequestParam double number1,
            @RequestParam double number2) {

        double result = calculatorService.multiply(number1, number2);
        return ResponseEntity.ok(
                new CalculatorResult("multiply", number1, number2, result)
        );
    }

    /**
     * Divides the first number by the second.
     * Division by zero is handled in the service layer.
     */
    @GetMapping("/divide")
    public ResponseEntity<CalculatorResult> divide(
            @RequestParam double number1,
            @RequestParam double number2) {

        double result = calculatorService.divide(number1, number2);
        return ResponseEntity.ok(
                new CalculatorResult("divide", number1, number2, result)
        );
    }
}
