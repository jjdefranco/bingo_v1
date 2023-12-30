package com.project.bingo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BingoCard {
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

    public Map<BingoColumn, ArrayList<BingoSquare>> cardMapping;

    public BingoCard() {
        this.cardMapping = new HashMap<>();
        this.cardMapping.put(BingoColumn.B, bColumnGenerator());
        this.cardMapping.put(BingoColumn.I, iColumnGenerator());
        this.cardMapping.put(BingoColumn.N, nColumnGenerator());
        this.cardMapping.put(BingoColumn.G, gColumnGenerator());
        this.cardMapping.put(BingoColumn.O, oColumnGenerator());
    }

    public boolean hasBingoSquare(String letter, String number) {
        ArrayList<BingoSquare> bingoColumn = this.cardMapping.get(letter);
        if (bingoColumn == null) {
            throw new RuntimeException(String.format("Invalid Input: %s-%s", letter, number));
        }
        boolean hasBingoSquare = false;
        for (BingoSquare bingoSquare : bingoColumn) {
            if (bingoSquare.isSquareMarked()) {
                continue;
            }
            if (Objects.equals(bingoSquare.number, number)) {
                bingoSquare.markSquare();
                hasBingoSquare = true;
                break;
            }
        }
        return hasBingoSquare;
    }

    public boolean isGameOver() {
        return this.hasWholeColumn() || this.hasWholeRow() || this.hasTopLeftDiagonal() || this.hasTopRightDiagonal();
    }
    private boolean hasWholeColumn() {
        boolean hasWholeColumn;
        for (Map.Entry<BingoColumn, ArrayList<BingoSquare>> cardEntry : this.cardMapping.entrySet()) {
            hasWholeColumn = false;
            for (BingoSquare bingoSquare : cardEntry.getValue()) {
                if (bingoSquare.isSquareMarked()) {
                    hasWholeColumn = true;
                } else {
                    hasWholeColumn = false;
                    break;
                }
            }
            if (hasWholeColumn) {
                return true;
            }
        }
        return false;
    }

    private boolean hasWholeRow() {
        boolean hasWholeRow = false;
        for (int index = 0; index < 5; index++) {
            BingoSquare bSquare = this.cardMapping.get(BingoColumn.B).get(index);
            BingoSquare iSquare = this.cardMapping.get(BingoColumn.I).get(index);
            BingoSquare nSquare = this.cardMapping.get(BingoColumn.N).get(index);
            BingoSquare gSquare = this.cardMapping.get(BingoColumn.G).get(index);
            BingoSquare oSquare = this.cardMapping.get(BingoColumn.O).get(index);

            if (bSquare.isSquareMarked() && iSquare.isSquareMarked() && nSquare.isSquareMarked()
                    && gSquare.isSquareMarked() && oSquare.isSquareMarked()) {
                hasWholeRow = true;
                break;
            }
        }
        return hasWholeRow;
    }

    private boolean hasTopLeftDiagonal() {
        BingoSquare bSquare = this.cardMapping.get(BingoColumn.B).get(0);
        BingoSquare iSquare = this.cardMapping.get(BingoColumn.I).get(1);
        BingoSquare nSquare = this.cardMapping.get(BingoColumn.N).get(2);
        BingoSquare gSquare = this.cardMapping.get(BingoColumn.G).get(3);
        BingoSquare oSquare = this.cardMapping.get(BingoColumn.O).get(4);

        return bSquare.isSquareMarked() && iSquare.isSquareMarked() && nSquare.isSquareMarked() 
                && gSquare.isSquareMarked() && oSquare.isSquareMarked();
    }

    private boolean hasTopRightDiagonal() {
        BingoSquare bSquare = this.cardMapping.get(BingoColumn.B).get(4);
        BingoSquare iSquare = this.cardMapping.get(BingoColumn.I).get(3);
        BingoSquare nSquare = this.cardMapping.get(BingoColumn.N).get(2);
        BingoSquare gSquare = this.cardMapping.get(BingoColumn.G).get(1);
        BingoSquare oSquare = this.cardMapping.get(BingoColumn.O).get(0);

        return bSquare.isSquareMarked() && iSquare.isSquareMarked() && nSquare.isSquareMarked()
                && gSquare.isSquareMarked() && oSquare.isSquareMarked();
    }

    private ArrayList<BingoSquare> bColumnGenerator() {
        ArrayList<BingoSquare> bColumn = new ArrayList<>();
        for (int bingoNumber: BingoNumberGenerator.generateRandomNumberArray(B_MIN_VALUE, B_MAX_VALUE)) {
            bColumn.add(new BingoSquare(BingoColumn.B, bingoNumber));
        }
        return bColumn;
    }

    private ArrayList<BingoSquare> iColumnGenerator() {
        ArrayList<BingoSquare> iColumn = new ArrayList<>();
        for (int bingoNumber: BingoNumberGenerator.generateRandomNumberArray(I_MIN_VALUE, I_MAX_VALUE)) {
            iColumn.add(new BingoSquare(BingoColumn.I, bingoNumber));
        }
        return iColumn;
    }

    private ArrayList<BingoSquare> nColumnGenerator() {
        ArrayList<BingoSquare> nColumn = new ArrayList<>();
        for (int bingoNumber: BingoNumberGenerator.generateRandomNumberArray(N_MIN_VALUE, N_MAX_VALUE)) {
            nColumn.add(new BingoSquare(BingoColumn.N, bingoNumber));
        }
        BingoSquare freeSquare = new BingoSquare(BingoColumn.N, "FREE");
        freeSquare.markSquare();
        nColumn.set(2, freeSquare);
        return nColumn;
    }

    private ArrayList<BingoSquare> gColumnGenerator() {
        ArrayList<BingoSquare> gColumn = new ArrayList<>();
        for (int bingoNumber: BingoNumberGenerator.generateRandomNumberArray(G_MIN_VALUE, G_MAX_VALUE)) {
            gColumn.add(new BingoSquare(BingoColumn.G, bingoNumber));
        }
        return gColumn;
    }

    private ArrayList<BingoSquare> oColumnGenerator() {
        ArrayList<BingoSquare> oColumn = new ArrayList<>();
        for (int bingoNumber: BingoNumberGenerator.generateRandomNumberArray(O_MIN_VALUE, O_MAX_VALUE)) {
            oColumn.add(new BingoSquare(BingoColumn.O, bingoNumber));
        }
        return oColumn;
    }
}
