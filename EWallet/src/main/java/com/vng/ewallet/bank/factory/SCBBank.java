package com.vng.ewallet.bank.factory;

import com.vng.ewallet.bank.Bank;
import com.vng.ewallet.exception.ApiRequestException;
import com.vng.ewallet.util.Regex;

public class SCBBank implements BankCheck {
    @Override
    public void check(Bank bank) {
        if (!checkCode(bank.getCode())) {
            throw new ApiRequestException("Invalid bank code");
        }
        if (!checkHolderName(bank.getHolderName())){
            throw new ApiRequestException("Invalid bank holder name");
        }
    }

    public boolean checkHolderName(String holderName) {
        return Regex.checkRegex(holderName, "^([A-Z ]+)$");
    }

    public boolean checkCode(String code) {
        return Regex.checkRegex(code, "(^\\d{9})(678$)");
    }
}
