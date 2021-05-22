package com.vng.ewallet.bank;

import com.vng.ewallet.bank.factory.BankFactory;
import com.vng.ewallet.bank.factory.BankCheck;
import com.vng.ewallet.bank.factory.ValidBank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
    private final Logger logger = LoggerFactory.getLogger(BankService.class);
    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<Bank> findAllBanks() {
        return bankRepository.findAll();
    }

    public ValidBank insertBank(Bank bank) {
        BankCheck bankCheck = BankFactory.getBankCheck(bank.getBankName());

        ValidBank res = bankCheck.check(bank);
        if(res == ValidBank.SUCCESS) {
            bankRepository.save(bank);
        }
        return res;

    }
}
