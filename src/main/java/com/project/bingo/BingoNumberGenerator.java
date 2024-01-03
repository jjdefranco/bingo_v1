package com.project.bingo;


import java.util.ArrayList;
import java.util.Random;

public class BingoNumberGenerator {

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

    public String generateRandomBingoSquare() {
        int randomNumber = this.generateRandomNumber(B_MIN_VALUE, O_MAX_VALUE);
        BingoColumnName result;
        if (randomNumber <= B_MAX_VALUE) {
            result = BingoColumnName.B;
        } else if (randomNumber <= I_MAX_VALUE) {
            result = BingoColumnName.I;
        } else if (randomNumber <= N_MAX_VALUE) {
            result = BingoColumnName.N;
        } else if (randomNumber <= G_MAX_VALUE) {
            result = BingoColumnName.G;
        } else if (randomNumber <= O_MAX_VALUE) {
            result = BingoColumnName.O;
        } else {
            throw new RuntimeException(String.format("Invalid Number Generated: %d", randomNumber));
        }
        return String.format("%s-%02d", result, randomNumber);
    }

    public ArrayList<BingoSquare> bColumnGenerator() {
        ArrayList<BingoSquare> bColumn = new ArrayList<>();
        for (int bingoNumber: generateRandomNumberArray(B_MIN_VALUE, B_MAX_VALUE)) {
            bColumn.add(new BingoSquare(BingoColumnName.B, bingoNumber));
        }
        return bColumn;
    }

    public ArrayList<BingoSquare> iColumnGenerator() {
        ArrayList<BingoSquare> iColumn = new ArrayList<>();
        for (int bingoNumber: generateRandomNumberArray(I_MIN_VALUE, I_MAX_VALUE)) {
            iColumn.add(new BingoSquare(BingoColumnName.I, bingoNumber));
        }
        return iColumn;
    }

    public ArrayList<BingoSquare> nColumnGenerator() {
        ArrayList<BingoSquare> nColumn = new ArrayList<>();
        for (int bingoNumber: generateRandomNumberArray(N_MIN_VALUE, N_MAX_VALUE)) {
            nColumn.add(new BingoSquare(BingoColumnName.N, bingoNumber));
        }
        BingoSquare freeSquare = new BingoSquare(BingoColumnName.N, "FREE");
        freeSquare.setMarkStatus(true);
        nColumn.set(2, freeSquare);
        return nColumn;
    }

    public ArrayList<BingoSquare> gColumnGenerator() {
        ArrayList<BingoSquare> gColumn = new ArrayList<>();
        for (int bingoNumber: generateRandomNumberArray(G_MIN_VALUE, G_MAX_VALUE)) {
            gColumn.add(new BingoSquare(BingoColumnName.G, bingoNumber));
        }
        return gColumn;
    }

    public ArrayList<BingoSquare> oColumnGenerator() {
        ArrayList<BingoSquare> oColumn = new ArrayList<>();
        for (int bingoNumber: generateRandomNumberArray(O_MIN_VALUE, O_MAX_VALUE)) {
            oColumn.add(new BingoSquare(BingoColumnName.O, bingoNumber));
        }
        return oColumn;
    }

    private ArrayList<Integer> generateRandomNumberArray(final int minimum, final int maximum) {
        ArrayList<Integer> randomNumberArray = new ArrayList<>();
        while (randomNumberArray.size() <= 5) {
            final int randomNumber = generateRandomNumber(minimum, maximum);
            if (!randomNumberArray.contains(randomNumber)) {
                randomNumberArray.add(randomNumber);
            }
        }
        return randomNumberArray;
    }

    private int generateRandomNumber(final int minimum, final int maximum) {
        final Random randomNumberGenerator = new Random();
        return randomNumberGenerator.nextInt(maximum - minimum) + minimum;
    }
}
