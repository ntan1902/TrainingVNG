package com.vng.ewallet.bank.factory;

import com.vng.ewallet.regex.Regex;

public class SCBBank implements BankCheck {
    @Override
    public boolean checkCode(String code) {
        return Regex.checkRegex(code, "(^\\d{9})(678$)");
    }
}
