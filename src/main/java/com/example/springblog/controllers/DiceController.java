package com.example.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Random;

@Controller
public class DiceController {

    @GetMapping("/roll-dice")
    public String showChoices() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{n}")
    public String showResults(@PathVariable int n, Model model) {
        Random rand = new Random();
        int randomNum = rand.nextInt(6 - 1 + 1) + 1;
        boolean result = n == randomNum;
        boolean notResult = n != randomNum;
        model.addAttribute("result", result);
        model.addAttribute("notResult", notResult);
        model.addAttribute("randomNum", randomNum);

        return "roll-dice";
    }
}
