package com.vng.ewallet.bank.factory;

import com.vng.ewallet.regex.Regex;

public class OCBBank implements BankCheck{
    @Override
    public boolean checkCode(String code) {
        return Regex.checkRegex(code, "^((?!012))\\d{9}[13579]$");
    }
}
