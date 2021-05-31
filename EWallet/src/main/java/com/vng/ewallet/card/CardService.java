package com.vng.ewallet.card;

import com.vng.ewallet.card.factory.CardCheck;
import com.vng.ewallet.card.factory.CardFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    private final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    public List<Card> findAllCards() {
        return this.cardRepository.findAll();
    }

    public Card findCardById(Long id) {
        return this.cardRepository.findById(id).orElse(null);
    }

    public Card insertCard(Card card) {
        checkIfCardIsValidate(card);
        return this.cardRepository.save(card);
    }

    public void checkIfCardIsValidate(Card card) {
        CardCheck cardCheck = CardFactory.getCardCheck(card.getCardName());
        cardCheck.check(card);
    }

    public Card updateCard(Long id, Card card) {
        // Check if card is exist
        Optional<Card> optionalCard = this.cardRepository.findById(id);
        if(optionalCard.isPresent()) {
            // Check validate new card
            checkIfCardIsValidate(card);
            return this.cardRepository.save(card);
        }
        return null;
    }

    public boolean deleteCard(Long id) {
        if(this.cardRepository.existsById(id)){
            this.cardRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
