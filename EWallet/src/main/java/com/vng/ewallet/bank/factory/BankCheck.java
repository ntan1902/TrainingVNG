package com.vng.ewallet.bank.factory;

import com.vng.ewallet.bank.Bank;
import com.vng.ewallet.exception.ApiRequestException;
import com.vng.ewallet.regex.Regex;

public interface BankCheck {
    default void check(Bank bank) {
        if (!checkCode(bank.getCode())) {
            throw new ApiRequestException("Invalid card number");
        }
        if (!checkHolderName(bank.getHolderName())){
            throw new ApiRequestException("Invalid card holder name");
        }
    }

    default boolean checkHolderName(String holderName) {
        return Regex.checkRegex(holderName, "^([A-Z ]+)$");
    }

    boolean checkCode(String code);
}
