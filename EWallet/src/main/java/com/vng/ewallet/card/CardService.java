package com.vng.ewallet.card;

import com.vng.ewallet.card.factory.CardCheck;
import com.vng.ewallet.card.factory.CardFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class CardService {
//    private final Logger log = LogManager.getLogger(CardService.class);
    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    public List<Card> findAllCards() {
        log.info("Inside findAllCards of CardService");
        return this.cardRepository.findAll();
    }

    public Card findCardById(Long id) {
        log.info("Inside findCardById of CardService");
        return this.cardRepository.findById(id).orElse(null);
    }

    public Card insertCard(Card card) {
        log.info("Inside insertCard of CardService");
        checkIfCardIsValidate(card);
        return this.cardRepository.save(card);
    }

    public void checkIfCardIsValidate(Card card) {
        log.info("Inside checkIfCardIsValidate of CardService");
        CardCheck cardCheck = CardFactory.getCardCheck(card.getCardName());
        cardCheck.check(card);
    }

    public Card updateCard(Long id, Card card) {
        log.info("Inside updateCard of CardService");
        // Check if card is exist
        Optional<Card> optionalCard = this.cardRepository.findById(id);
        if(optionalCard.isPresent()) {
            // Check validate new card
            checkIfCardIsValidate(card);
            return this.cardRepository.save(card);
        } else {
            log.error("Inside updateCard of CardService: Card doesn't exist");
        }
        return null;
    }

    public boolean deleteCard(Long id) {
        log.info("Inside deleteCard of CardService");
        if(this.cardRepository.existsById(id)){
            this.cardRepository.deleteById(id);
            return true;
        } else {
            log.error("Inside deleteCard of CardService: Card doesn't exist");
        }
        return false;
    }
}
