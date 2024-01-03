package com.project.bingo;

import com.project.bingo.requests.BingoSquareBody;
import com.project.bingo.responses.BingoCardResponse;
import com.project.bingo.responses.BingoNumberResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BingoController {

    BingoCard bingoCard = null;

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/bingo-number")
    public ResponseEntity<BingoNumberResponse> getRandomBingoNumber() {
        String newBingoNumber = new BingoNumberGenerator().generateRandomBingoSquare();
        return new ResponseEntity<>(new BingoNumberResponse(newBingoNumber), HttpStatus.OK);
    }

    @GetMapping("/bingo-card")
    public ResponseEntity<BingoCardResponse> getBingoCard() {
        if (this.bingoCard == null) {
            throw new RuntimeException("BingoCard has not been created yet");
        }
        return new ResponseEntity<>(new BingoCardResponse(this.bingoCard.cardMapping), HttpStatus.OK);
    }

    @PostMapping("/bingo-card")
    public ResponseEntity<BingoCardResponse> createBingoCard() {
        this.bingoCard = new BingoCard();
        return new ResponseEntity<>(new BingoCardResponse(this.bingoCard.cardMapping), HttpStatus.CREATED);
    }

    @PatchMapping("/bingo-card")
    public ResponseEntity<BingoCardResponse> updateBingoCard(@RequestBody final BingoSquareBody payload) {
        if (this.bingoCard == null) {
            throw new RuntimeException("BingoCard has not been created yet");
        }
        BingoSquare bingoSquare = this.bingoCard.getBingoSquareOrNull(payload.column, payload.number);
        if (bingoSquare == null) {
            throw new RuntimeException("BingoCard does not have that square");
        }
        bingoSquare.setMarkStatus(payload.isMarked);
        return new ResponseEntity<>(new BingoCardResponse(this.bingoCard.cardMapping), HttpStatus.OK);
    }
}
