package com.cct.calculator_webservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalculatorWebserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CalculatorWebserviceApplication.class, args);
		System.out.println("Calculator web service started...");
	}
}
