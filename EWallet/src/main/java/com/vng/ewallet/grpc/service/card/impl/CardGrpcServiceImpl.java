package com.vng.ewallet.grpc.service.card.impl;

import com.vng.ewallet.entity.Card;
import com.vng.ewallet.entity.CardRepository;
import com.vng.ewallet.exception.ApiRequestException;
import com.vng.ewallet.factory.card.CardCheck;
import com.vng.ewallet.factory.card.CardFactory;
import com.vng.ewallet.grpc.service.card.CardGrpcService;
import grpc.card.CardItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class CardGrpcServiceImpl implements CardGrpcService {
    private final CardRepository cardRepository;

    @Override
    public CardItem findCardItemById(Long id) {
        log.info("Inside findCardById of CardGrpcServiceImpl");
        Card card = this.cardRepository.findById(id).orElse(null);
        return this.convertToCardItem(card);
    }

    @Override
    public CardItem insertCardItem(CardItem cardItem) {
        log.info("Inside insertCardItem of CardGrpcServiceImpl");

        checkIfCardItemIsValidate(cardItem);

        this.cardRepository.save(this.convertToCard(cardItem));

        return cardItem;
    }

    @Override
    public void checkIfCardItemIsValidate(CardItem cardItem) {
        log.info("Inside checkIfCardItemIsValidate of CardGrpcServiceImpl");
        CardCheck cardCheck = CardFactory.getCardCheck(cardItem.getCardName());
        cardCheck.check(this.convertToCard(cardItem));
    }

    @Override
    public CardItem updateCardItem(CardItem cardItem) {
        log.info("Inside updateCardItem of CardGrpcServiceImpl");
        // Check if card is exist
        Optional<Card> optionalCard = this.cardRepository.findById(cardItem.getId());
        if(optionalCard.isPresent()) {
            // Check validate new card
            checkIfCardItemIsValidate(cardItem);

            this.cardRepository.save(this.convertToCard(cardItem));

            return cardItem;
        } else {
            log.error("Inside updateCardItem of CardGrpcServiceImpl: Card doesn't exist");
        }
        return null;
    }

    @Override
    public boolean deleteCardItem(Long id) {
        log.info("Inside deleteCardItem of CardGrpcServiceImpl");
        if(this.cardRepository.existsById(id)){
            this.cardRepository.deleteById(id);
            return true;
        } else {
            log.error("Inside deleteCardItem of CardGrpcServiceImpl: Card doesn't exist");
            throw new ApiRequestException("Card doesn't exist");
        }
    }

    private Card convertToCard(CardItem cardItem) {
        return new Card(
                cardItem.getId(),
                cardItem.getCardName(),
                cardItem.getCode()
        );
    }

    private CardItem convertToCardItem(Card card) {
        return CardItem.newBuilder()
                .setId(card.getId())
                .setCardName(card.getCardName())
                .setCode(card.getCode())
                .build();
    }
}
