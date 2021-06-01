package com.vng.ewallet.bank;

import com.vng.ewallet.bank.factory.BankFactory;
import com.vng.ewallet.bank.factory.BankCheck;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class BankService {
//    private final Logger log = LogManager.getLogger(BankService.class);
    private final BankRepository bankRepository;

    @Autowired
    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<Bank> findAllBanks() {
        log.info("Inside findAllBanks of BankService");
        try {
            return this.bankRepository.findAll();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public Bank findBankById(Long id) {
        log.info("Inside findBankById of BankService");
        return this.bankRepository
                .findById(id)
                .orElse(null);
    }

    public Bank insertBank(Bank bank) {
        log.info("Inside insertBank of BankService");
        checkIfBankIsValidate(bank);
        return this.bankRepository.save(bank);

    }


    public void checkIfBankIsValidate(Bank bank) {
        log.info("Inside checkIfBankIsValidate of BankService");
        BankCheck bankCheck = BankFactory.getBankCheck(bank.getBankName());
        bankCheck.check(bank);
    }

    public Bank updateBank(Long id, Bank bank) {
        log.info("Inside updateBank of BankService");
        Optional<Bank> optionalBank = this.bankRepository.findById(id);
        if(optionalBank.isPresent()) {
            // Check validate new bank
            checkIfBankIsValidate(bank);
            return this.bankRepository.save(bank);
        } else {
            log.error("Inside updateBank of BankService: Bank doesn't exist");
        }

        return null;
    }

    public boolean deleteBank(Long id) {
        log.info("Inside deleteBank of BankService");
        if(this.bankRepository.existsById(id)) {
            this.bankRepository.deleteById(id);
            return true;
        } else {
            log.error("Inside updateBank of BankService: Bank doesn't exist");
        }
        return false;
    }

    public boolean findBank(Bank bank) {
        log.info("Inside findBank of BankService");
        Bank optionalBank = this.bankRepository.findBankByCode(bank.getCode());
        return optionalBank != null;
    }
}
