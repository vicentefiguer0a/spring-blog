package com.example.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
public class DiceController {

    @GetMapping("/roll-dice/{n}")
    @ResponseBody
    public String diceGuess(@PathVariable int n) {
        Random rand = new Random();
        int randomNum = rand.nextInt(6 - 1 + 1) + 1;
        if (randomNum == n) {
            return "Your number was " + n + ".\nThe number to guess was " + randomNum + ".\nYou guessed correctly.";
        } else {
            return "Your number was " + n + ".\nThe number to guess was " + randomNum + ".\nYou guessed incorrectly.";
        }
    }
}
