package com.project.bingo;

public class BingoSquare {
    public String letter;
    public String number;
    private boolean marked = false;

    public BingoSquare(final BingoColumn letter, final String number) {
        this.letter = letter.toString();
        this.number = number;
    }

    public BingoSquare(final BingoColumn letter, final int number) {
        this.letter = letter.toString();
        this.number = String.format("%02d", number);
    }

    public boolean isSquareMarked() {
        return this.marked;
    }

    public void markSquare() {
        this.marked = true;
    }

    public String getSquareValue() {
        return String.format("%s-%s", letter, number);
    }

    @Override
    public String toString() {
        return String.format("BingoSquare(letter=%s, number=%s, marked=%s)", letter, number, marked);
    }
}
