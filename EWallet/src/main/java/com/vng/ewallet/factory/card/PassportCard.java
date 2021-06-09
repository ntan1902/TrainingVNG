package com.vng.ewallet.factory.card;

import com.vng.ewallet.entity.Card;
import com.vng.ewallet.exception.ApiRequestException;
import com.vng.ewallet.util.Regex;

public class PassportCard implements CardCheck {
    @Override
    public void check(Card card) {
        if(!Regex.checkRegex(card.getCode(), "^[A-Z]\\d{7}$")) {
            throw new ApiRequestException("Invalid card code");
        }
    }
}
