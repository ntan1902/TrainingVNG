package com.vng.ewallet.bank;

import com.vng.ewallet.bank.factory.BankFactory;
import com.vng.ewallet.bank.factory.BankCheck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {
    private final Logger logger = LogManager.getLogger(BankService.class);
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

    public Bank findBankById(Long id) {
        return this.bankRepository
                .findById(id)
                .orElse(null);
    }

    public Bank insertBank(Bank bank) {
        checkIfBankIsValidate(bank);
        return this.bankRepository.save(bank);

    }


    public void checkIfBankIsValidate(Bank bank) {
        BankCheck bankCheck = BankFactory.getBankCheck(bank.getBankName());
        bankCheck.check(bank);
    }

    public Bank updateBank(Long id, Bank bank) {
        Optional<Bank> optionalBank = this.bankRepository.findById(id);
        if(optionalBank.isPresent()) {
            // Check validate new bank
            checkIfBankIsValidate(bank);

            return this.bankRepository.save(bank);
        }

        return null;
    }

    public boolean deleteBank(Long id) {
        if(this.bankRepository.existsById(id)) {
            this.bankRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean findBank(Bank bank) {
        Bank optionalBank = this.bankRepository.findBankByCode(bank.getCode());
        return optionalBank != null;
    }
}
