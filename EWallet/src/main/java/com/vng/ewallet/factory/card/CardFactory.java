package com.vng.ewallet.factory.card;

import com.vng.ewallet.exception.ApiRequestException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CardFactory {
    private static final Map<String, CardCheck> map = new HashMap<>();

    @PostConstruct
    private Map<String, CardCheck> initialize() {
        map.put("CMND", new CMNDCard());
        map.put("CCCD", new CCCDCard());
        map.put("Passport", new PassportCard());
        return map;
    }

    public static final CardCheck getCardCheck(String cardName){
        return Optional
                .ofNullable(map.get(cardName))
                .orElseThrow(() -> new ApiRequestException(cardName + " is not supported"));
    }
}
