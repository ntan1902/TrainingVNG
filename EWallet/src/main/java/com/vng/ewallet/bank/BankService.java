package com.vng.ewallet.bank;

import com.vng.ewallet.bank.factory.BankFactory;
import com.vng.ewallet.bank.factory.BankCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankService {
    private final Logger logger = LoggerFactory.getLogger(BankService.class);
    private final BankRepository bankRepository;

    @Autowired
    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<Bank> findAllBanks() {
        try {
            return this.bankRepository.findAll();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public Bank insertBank(Bank bank) {
        BankCheck bankCheck = BankFactory.getBankCheck(bank.getBankName());
        bankCheck.check(bank);
        return this.bankRepository.save(bank);

    }
}
