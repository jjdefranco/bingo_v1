package com.project.bingo;

import java.util.ArrayList;
import java.util.Random;

public class BingoNumberGenerator {

    static int generateRandomNumber(final int minimum, final int maximum) {
        final Random randomNumberGenerator = new Random();
        return randomNumberGenerator.nextInt(maximum - minimum) + minimum;
    }

    static ArrayList<Integer> generateRandomNumberArray(final int minimum, final int maximum) {
        ArrayList<Integer> randomNumberArray = new ArrayList<>();
        while (randomNumberArray.size() <= 5) {
            final int randomNumber = generateRandomNumber(minimum, maximum);
            if (!randomNumberArray.contains(randomNumber)) {
                randomNumberArray.add(randomNumber);
            }
        }
        return randomNumberArray;
    }
}
