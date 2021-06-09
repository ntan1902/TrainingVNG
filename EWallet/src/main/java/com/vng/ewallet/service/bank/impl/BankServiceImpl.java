package com.vng.ewallet.service.bank.impl;

import com.vng.ewallet.entity.Bank;
import com.vng.ewallet.entity.BankRepository;
import com.vng.ewallet.exception.ApiRequestException;
import com.vng.ewallet.factory.bank.BankFactory;
import com.vng.ewallet.factory.bank.BankCheck;
import com.vng.ewallet.service.bank.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    @Override
    public List<Bank> findAllBanks() {
        log.info("Inside findAllBanks of BankService");
        return this.bankRepository.findAll();

    }

    @Override
    public Bank findBankById(Long id) {
        log.info("Inside findBankById of BankService");
        return this.bankRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public Bank insertBank(Bank bank) throws ApiRequestException{
        log.info("Inside insertBank of BankService");
        checkIfBankIsValidate(bank);
        return this.bankRepository.save(bank);

    }


    @Override
    public void checkIfBankIsValidate(Bank bank) throws ApiRequestException{
        log.info("Inside checkIfBankIsValidate of BankService");
        BankCheck bankCheck = BankFactory.getBankCheck(bank.getBankName());
        bankCheck.check(bank);
    }

    @Override
    public Bank updateBank(Long id, Bank bank) throws ApiRequestException{
        log.info("Inside updateBank of BankService");
        Optional<Bank> optionalBank = this.bankRepository.findById(id);
        if (optionalBank.isPresent()) {
            // Check validate new bank
            checkIfBankIsValidate(bank);
            return this.bankRepository.save(bank);
        } else {
            log.error("Inside updateBank of BankService: Bank doesn't exist");
        }

        return null;
    }

    @Override
    public boolean deleteBank(Long id) {
        log.info("Inside deleteBank of BankService");
        if (this.bankRepository.existsById(id)) {
            this.bankRepository.deleteById(id);
            return true;
        } else {
            log.error("Inside updateBank of BankService: Bank doesn't exist");
        }
        return false;
    }

    @Override
    public boolean findBankByCode(String code) {
        log.info("Inside findBank of BankService");
        Bank optionalBank = this.bankRepository.findBankByCode(code);
        return optionalBank != null;
    }
}
