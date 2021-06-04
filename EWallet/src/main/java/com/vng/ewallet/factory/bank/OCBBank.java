package com.vng.ewallet.factory.bank;

import com.vng.ewallet.entity.Bank;
import com.vng.ewallet.exception.ApiRequestException;
import com.vng.ewallet.util.Regex;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class OCBBank implements BankCheck {
    @Override
    public void check(Bank bank) {
        if (!checkCode(bank.getCode())) {
            log.error("Inside check of OCBBank: Invalid bank code");
            throw new ApiRequestException("Invalid bank code");
        }
        if (!checkHolderName(bank.getHolderName())){
            log.error("Inside check of OCBBank: Invalid bank holder name");
            throw new ApiRequestException("Invalid bank holder name");
        }
    }

    public boolean checkHolderName(String holderName) {
        return Regex.checkRegex(holderName, "^([A-Z ]+)$");
    }

    public boolean checkCode(String code) {
        return Regex.checkRegex(code, "^((?!012))\\d{9}[13579]$");
    }
}
