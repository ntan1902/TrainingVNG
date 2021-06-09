package com.vng.ewallet.grpc.service.card;

import grpc.card.CardItem;

public interface CardGrpcService {

    CardItem findCardById(Long id);

    CardItem insertCardItem(CardItem cardItem);

    void checkIfCardItemIsValidate(CardItem cardItem);

    CardItem updateCardItem(Long id, CardItem cardItem);

    boolean deleteCardItem(Long id);
}
