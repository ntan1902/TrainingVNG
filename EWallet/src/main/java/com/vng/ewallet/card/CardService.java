package com.vng.ewallet.card;

import com.vng.ewallet.card.factory.CardCheck;
import com.vng.ewallet.card.factory.CardFactory;
import com.vng.ewallet.exception.ApiRequestException;
import com.vng.ewallet.user.User;
import com.vng.ewallet.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    private final CardRepository cardRepository;
    private final UserRepository userRepository;

    @Autowired
    public CardService(CardRepository cardRepository, UserRepository userRepository) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
    }


    public List<Card> findAllCards() {
        return this.cardRepository.findAll();
    }

    public Card findCardById(Long id) {
        Optional<Card> optionalCard = this.cardRepository.findById(id);
        return optionalCard.orElse(null);
    }

    public Card insertCard(Card card) {
        check(card);
        return this.cardRepository.save(card);
    }

    private void check(Card card) {
        checkIfUserIsExist(card);
        checkIfCardIsValidate(card);
    }

    private void checkIfUserIsExist(Card card) {
        Optional<User> optionalUser = this.userRepository.findById(card.getUser().getId());
        if(optionalUser.isPresent()) {
            card.setUser(optionalUser.get());
        } else {
            throw new ApiRequestException("User is not exist");
        }
    }

    private void checkIfCardIsValidate(Card card) {
        CardCheck cardCheck = CardFactory.getCardCheck(card.getCardName());
        cardCheck.check(card);
    }

    public Card updateCard(Long id, Card card) {
        // Check if card is exist
        Optional<Card> optionalCard = this.cardRepository.findById(id);
        if(optionalCard.isPresent()) {
            // Check validate new card
            checkIfCardIsValidate(card);

            // Update card
            Card oldCard = optionalCard.get();

            // Set user
            card.setUser(oldCard.getUser());

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
