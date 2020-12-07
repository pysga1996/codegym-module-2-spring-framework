package com.codegym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @GetMapping("/")
    public String showCalculator() {
        return "index";
    }

    @PostMapping("/result")
    public String calculate(@RequestParam("action") String action,
        @RequestParam("first_operand") String operand1,
        @RequestParam("second_operand") String operand2, Model model) {
        try {
            double first_operand = Double.parseDouble(operand1);
            double second_operand = Double.parseDouble(operand2);
            double result = 0;
            String operator = "";
            switch (action) {
                case "Addition(+)":
                    result = first_operand + second_operand;
                    operator = "Addition";
                    break;
                case "Subtraction(-)":
                    result = first_operand - second_operand;
                    operator = "Subtraction";
                    break;
                case "Multiplication(*)":
                    result = first_operand * second_operand;
                    operator = "Multiplication";
                    break;
                case "Division(/)":
                    result = first_operand / second_operand;
                    operator = "Division";
                    break;
            }
            model.addAttribute("result", result);
            model.addAttribute("operator", operator);
            model.addAttribute("first_operand", first_operand);
            model.addAttribute("second_operand", second_operand);
            return "index";
        } catch (NumberFormatException e) {
            return "index";
        }

    }
}
