package com.project.bingo.responses;

import com.project.bingo.BingoColumnName;
import com.project.bingo.BingoSquare;

import java.util.ArrayList;
import java.util.HashMap;

public class BingoCardResponse {

    public HashMap<BingoColumnName, ArrayList<BingoSquare>> card;

    public BingoCardResponse(HashMap<BingoColumnName, ArrayList<BingoSquare>> card) {
        this.card = card;
    }

}
