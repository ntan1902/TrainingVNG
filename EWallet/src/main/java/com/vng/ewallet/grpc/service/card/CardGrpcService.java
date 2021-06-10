package com.vng.ewallet.grpc.service.card;

import grpc.card.CardItem;

public interface CardGrpcService {

    CardItem findCardItemById(Long id);

    CardItem insertCardItem(CardItem cardItem);

    void checkIfCardItemIsValidate(CardItem cardItem);

    CardItem updateCardItem(CardItem cardItem);

    boolean deleteCardItem(Long id);
}
