package com.vng.ewallet.bank.factory;

import com.vng.ewallet.regex.Regex;

public class VCBBank implements BankCheck {

    @Override
    public boolean checkCode(String code) {
        return Regex.checkRegex(code, "(^970436)(\\d{13}$)");
    }
}
