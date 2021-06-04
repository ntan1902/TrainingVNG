package com.vng.ewallet.service.bank;

import com.vng.ewallet.entity.Bank;

import java.util.List;

public interface BankService {
    List<Bank> findAllBanks();

    Bank findBankById(Long id);

    boolean findBankByCode(String code);

    Bank insertBank(Bank bank);

    Bank updateBank(Long id, Bank bank);

    boolean deleteBank(Long id);

    void checkIfBankIsValidate(Bank bank);
}
