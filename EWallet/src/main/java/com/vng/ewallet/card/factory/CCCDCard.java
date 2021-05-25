package com.vng.ewallet.card.factory;

import com.vng.ewallet.card.Card;
import com.vng.ewallet.exception.ApiRequestException;
import com.vng.ewallet.regex.Regex;

public class CCCDCard implements CardCheck {
    @Override
    public void check(Card card) {
        if(Regex.checkRegex(card.getCode(), "^\\d{12}$")) {
            throw new ApiRequestException("Invalid card code");
        }
    }
}
