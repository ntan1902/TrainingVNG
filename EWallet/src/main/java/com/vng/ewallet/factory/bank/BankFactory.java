package com.vng.ewallet.factory.bank;

import com.vng.ewallet.exception.ApiRequestException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class BankFactory {

    private static final Map<String, BankCheck> map = new HashMap<>();

    @PostConstruct
    private Map<String, BankCheck> initialize() {
        map.put("VCB", new VCBBank());
        map.put("SCB", new SCBBank());
        map.put("OCB", new OCBBank());
        return map;
    }

    public static final BankCheck getBankCheck(String bankName) throws ApiRequestException{
        return Optional
                .ofNullable(map.get(bankName))
                .orElseThrow(() -> new ApiRequestException(bankName + " is not supported"));
    }
}
