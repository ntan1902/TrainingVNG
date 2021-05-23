package com.vng.ewallet.bank.factory;

import com.vng.ewallet.bank.Bank;
import com.vng.ewallet.exception.ApiRequestException;
import com.vng.ewallet.regex.Regex;

public interface BankCheck {
    void check(Bank bank);
}
