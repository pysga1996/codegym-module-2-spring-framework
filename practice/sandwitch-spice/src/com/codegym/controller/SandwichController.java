package com.codegym.controller;

import model.Condiment;
import model.Sandwich;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SandwichController {

    private final Sandwich sandwich = new Sandwich();

    @GetMapping("/")
    public String showSandwichCondimentsSelection() {
        return "index";
    }

    @PostMapping("/save")
    public String saveSandwichCondiments(@RequestParam("condiments") String[] condimentsList,
        Model model) {
        for (String s : condimentsList) {
            if (s != null) {
                sandwich.addCondiment(new Condiment(s));
            }
        }
        model.addAttribute("condimentsList", sandwich.getCondimentsList());
        return "sandwich";
    }
}
