package com.project.bingo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

@RestController
public class BingoController {
    final static int B_MIN_VALUE = 1;
    final static int B_MAX_VALUE = 15;
    final static int I_MIN_VALUE = 16;
    final static int I_MAX_VALUE = 30;
    final static int N_MIN_VALUE = 31;
    final static int N_MAX_VALUE = 45;
    final static int G_MIN_VALUE = 46;
    final static int G_MAX_VALUE = 60;
    final static int O_MIN_VALUE = 61;
    final static int O_MAX_VALUE = 75;


    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/generate-number")
    public String generate_bingo_number() {
        int randomNumber = BingoNumberGenerator.generateRandomNumber(B_MIN_VALUE, O_MAX_VALUE);
        String result = "";
        if (randomNumber <= B_MAX_VALUE) {
            result = "B";
        } else if (randomNumber <= I_MAX_VALUE) {
            result = "I";
        } else if (randomNumber <= N_MAX_VALUE) {
            result = "N";
        } else if (randomNumber <= G_MAX_VALUE) {
            result = "G";
        } else if (randomNumber <= O_MAX_VALUE) {
            result = "O";
        } else {
            throw new RuntimeException(String.format("Invalid Number Generated: %d", randomNumber));
        }
        return String.format("New Number: %s-%02d", result, randomNumber);
    }

    @GetMapping("/generate-card")
    public HashMap<BingoColumn, ArrayList<BingoSquare>> generate_bingo_card() {
        return (HashMap<BingoColumn, ArrayList<BingoSquare>>) new BingoCard().cardMapping;
    }
}
