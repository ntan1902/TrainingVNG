package com.vng.ewallet.bank.factory;

import com.vng.ewallet.bank.Bank;
import com.vng.ewallet.exception.ApiRequestException;
import com.vng.ewallet.regex.Regex;

public class OCBBank implements BankCheck{
    @Override
    public void check(Bank bank) {
        if (!checkCode(bank.getCode())) {
            throw new ApiRequestException("Invalid card number");
        }
        if (!checkHolderName(bank.getHolderName())){
            throw new ApiRequestException("Invalid card holder name");
        }
    }

    public boolean checkHolderName(String holderName) {
        return Regex.checkRegex(holderName, "^([A-Z ]+)$");
    }

    public boolean checkCode(String code) {
        return Regex.checkRegex(code, "^((?!012))\\d{9}[13579]$");
    }
}
