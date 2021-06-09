package com.vng.ewallet.factory.bank;

import com.vng.ewallet.entity.Bank;
import com.vng.ewallet.exception.ApiRequestException;

public interface BankCheck {
    void check(Bank bank) throws ApiRequestException;
}
