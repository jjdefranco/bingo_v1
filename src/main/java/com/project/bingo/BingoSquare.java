package com.project.bingo;

public class BingoSquare {
    public String letter;
    public String number;
    private boolean marked = false;

    public BingoSquare(final BingoColumnName letter, final String number) {
        this.letter = letter.toString();
        this.number = number;
    }

    public BingoSquare(final BingoColumnName letter, final int number) {
        this.letter = letter.toString();
        this.number = String.format("%02d", number);
    }

    public boolean isSquareMarked() {
        return this.marked;
    }

    public void setMarkStatus(boolean markStatus) {
        this.marked = markStatus;
    }

    public String getSquareValue() {
        return String.format("%s-%s", letter, number);
    }

    @Override
    public String toString() {
        return String.format("BingoSquare(letter=%s, number=%s, marked=%s)", letter, number, marked);
    }
}
