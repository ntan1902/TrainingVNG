package com.vng.ewallet.grpc.service.card.impl;

import com.vng.ewallet.entity.Card;
import com.vng.ewallet.factory.card.CardCheck;
import com.vng.ewallet.factory.card.CardFactory;
import com.vng.ewallet.grpc.service.card.CardGrpcService;
import grpc.card.CardItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class CardGrpcServiceImpl implements CardGrpcService {
    @Override
    public CardItem findCardById(Long id) {
        // TODO
        return null;
    }

    @Override
    public CardItem insertCardItem(CardItem cardItem) {
        // TODO
        return null;
    }

    @Override
    public void checkIfCardItemIsValidate(CardItem cardItem) {
        log.info("Inside checkIfCardItemIsValidate of CardGrpcServiceImpl");
        CardCheck cardCheck = CardFactory.getCardCheck(cardItem.getCardName());
        cardCheck.check(this.convertToCard(cardItem));
    }

    @Override
    public CardItem updateCardItem(Long id, CardItem cardItem) {
        // TODO
        return null;
    }

    @Override
    public boolean deleteCardItem(Long id) {
        // TODO
        return false;
    }

    private Card convertToCard(CardItem cardItem) {
        return new Card(
                cardItem.getId(),
                cardItem.getCardName(),
                cardItem.getCode()
        );
    }
}
