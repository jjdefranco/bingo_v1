package com.project.bingo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BingoCard {

    public HashMap<BingoColumnName, ArrayList<BingoSquare>> cardMapping;

    public BingoCard() {
        this.cardMapping = new HashMap<>();
        setupBingoCard();
    }

    private void setupBingoCard() {
        BingoNumberGenerator randomNumberGenerator = new BingoNumberGenerator();
        this.cardMapping.put(BingoColumnName.B, randomNumberGenerator.bColumnGenerator());
        this.cardMapping.put(BingoColumnName.I, randomNumberGenerator.iColumnGenerator());
        this.cardMapping.put(BingoColumnName.N, randomNumberGenerator.nColumnGenerator());
        this.cardMapping.put(BingoColumnName.G, randomNumberGenerator.gColumnGenerator());
        this.cardMapping.put(BingoColumnName.O, randomNumberGenerator.oColumnGenerator());
    }

    public BingoSquare getBingoSquareOrNull(BingoColumnName letter, String number) {
        ArrayList<BingoSquare> bingoColumn = this.cardMapping.get(letter);
        if (bingoColumn == null) {
            throw new RuntimeException(String.format("Invalid Input: %s-%s", letter, number));
        }
        for (BingoSquare bingoSquare : bingoColumn) {
            if (Objects.equals(bingoSquare.number, number)) {
                return bingoSquare;
            }
        }
        return null;
    }

    public boolean isGameOver() {
        return this.hasWholeColumn() || this.hasWholeRow() || this.hasTopLeftDiagonal() || this.hasTopRightDiagonal();
    }

    private boolean hasWholeColumn() {
        boolean hasWholeColumn;
        for (Map.Entry<BingoColumnName, ArrayList<BingoSquare>> cardEntry : this.cardMapping.entrySet()) {
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
            BingoSquare bSquare = this.cardMapping.get(BingoColumnName.B).get(index);
            BingoSquare iSquare = this.cardMapping.get(BingoColumnName.I).get(index);
            BingoSquare nSquare = this.cardMapping.get(BingoColumnName.N).get(index);
            BingoSquare gSquare = this.cardMapping.get(BingoColumnName.G).get(index);
            BingoSquare oSquare = this.cardMapping.get(BingoColumnName.O).get(index);

            if (bSquare.isSquareMarked() && iSquare.isSquareMarked() && nSquare.isSquareMarked()
                    && gSquare.isSquareMarked() && oSquare.isSquareMarked()) {
                hasWholeRow = true;
                break;
            }
        }
        return hasWholeRow;
    }

    private boolean hasTopLeftDiagonal() {
        BingoSquare bSquare = this.cardMapping.get(BingoColumnName.B).get(0);
        BingoSquare iSquare = this.cardMapping.get(BingoColumnName.I).get(1);
        BingoSquare nSquare = this.cardMapping.get(BingoColumnName.N).get(2);
        BingoSquare gSquare = this.cardMapping.get(BingoColumnName.G).get(3);
        BingoSquare oSquare = this.cardMapping.get(BingoColumnName.O).get(4);

        return bSquare.isSquareMarked() && iSquare.isSquareMarked() && nSquare.isSquareMarked()
                && gSquare.isSquareMarked() && oSquare.isSquareMarked();
    }

    private boolean hasTopRightDiagonal() {
        BingoSquare bSquare = this.cardMapping.get(BingoColumnName.B).get(4);
        BingoSquare iSquare = this.cardMapping.get(BingoColumnName.I).get(3);
        BingoSquare nSquare = this.cardMapping.get(BingoColumnName.N).get(2);
        BingoSquare gSquare = this.cardMapping.get(BingoColumnName.G).get(1);
        BingoSquare oSquare = this.cardMapping.get(BingoColumnName.O).get(0);

        return bSquare.isSquareMarked() && iSquare.isSquareMarked() && nSquare.isSquareMarked()
                && gSquare.isSquareMarked() && oSquare.isSquareMarked();
    }
}
