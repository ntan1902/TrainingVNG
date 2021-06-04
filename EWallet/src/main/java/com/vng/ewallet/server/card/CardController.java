package com.vng.ewallet.server.card;

import com.vng.ewallet.entity.Card;
import com.vng.ewallet.service.card.CardService;
import com.vng.ewallet.service.card.impl.CardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @GetMapping
    public ResponseEntity<List<Card>> findAllCards() {
        List<Card> cards = this.cardService.findAllCards();
        if(!cards.isEmpty()) {
            return new ResponseEntity<>(cards, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> findCardById(@PathVariable("id") Long id) {
        Card card = this.cardService.findCardById(id);
        if(card != null) {
            return new ResponseEntity<>(card, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @PostMapping
//    public ResponseEntity<?> insertCard(@Valid @RequestBody Card card, BindingResult result) {
//        Map<String, String> err = Validate.checkValidate(result);
//
//        if (err == null) {
//            Card res = this.cardService.insertCard(card);
//            return new ResponseEntity<>(res, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
//
//    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<?> updateCard(@PathVariable("id") Long id, @Valid @RequestBody Card card,  BindingResult result) {
//        Map<String, String> err = Validate.checkValidate(result);
//
//        if (err == null) {
//            Card res = this.cardService.updateCard(id, card);
//            return new ResponseEntity<>(res, HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteCard(@PathVariable("id") Long id) {
//        if(this.cardService.deleteCard(id)) {
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }

}
