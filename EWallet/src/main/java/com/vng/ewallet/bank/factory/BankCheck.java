package com.vng.ewallet.bank.factory;

import com.vng.ewallet.bank.Bank;
import com.vng.ewallet.regex.Regex;

public interface BankCheck {
    default ValidBank check(Bank bank) {
        if (!checkCode(bank.getCode())) return ValidBank.INVALID_CODE;
        if (!checkHolderName(bank.getHolderName())) return ValidBank.INVALID_HOLDER_NAME;

        return ValidBank.SUCCESS;
    }

    default boolean checkHolderName(String holderName) {
        return Regex.checkRegex(holderName, "^([A-Z ]+)$");
    }

    boolean checkCode(String code);
}
