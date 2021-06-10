package com.vng.ewallet.grpc.service.bank.impl;

import com.vng.ewallet.entity.Bank;
import com.vng.ewallet.entity.BankRepository;
import com.vng.ewallet.exception.ApiRequestException;
import com.vng.ewallet.factory.bank.BankCheck;
import com.vng.ewallet.factory.bank.BankFactory;
import com.vng.ewallet.grpc.service.bank.BankGrpcService;
import grpc.bank.BankItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Log4j2
@RequiredArgsConstructor
public class BankGrpcServiceImpl implements BankGrpcService {
    private final BankRepository bankRepository;


    @Override
    public boolean findBankItemByCode(String code) {
        log.info("Inside findBankItem of BankItemServiceImpl");
        Bank optionalBank = this.bankRepository.findBankByCode(code);
        return optionalBank != null;
    }

    @Override
    public BankItem insertBankItem(BankItem bankItem) throws ApiRequestException{
        log.info("Inside insertBankItem of BankGrpcServiceImpl");

        checkIfBankItemIsValidate(bankItem);

        this.bankRepository.save(this.convertToBank(bankItem));

        return bankItem;
    }


    @Override
    public BankItem updateBankItem(BankItem bankItem) {
        log.info("Inside updateBankItem of BankGrpcServiceImpl");
        Optional<Bank> optionalBank = this.bankRepository.findById(bankItem.getId());
        if (optionalBank.isPresent()) {
            // Check validate new bank
            checkIfBankItemIsValidate(bankItem);

            this.bankRepository.save(this.convertToBank(bankItem));

            return bankItem;
        } else {
            log.error("Inside updateBankItem of BankGrpcServiceImpl: Bank doesn't exist");
        }

        return null;
    }

    @Override
    public boolean deleteBankItem(Long id) {
        log.info("Inside deleteBankItem of BankGrpcServiceImpl");
        if (this.bankRepository.existsById(id)) {
            this.bankRepository.deleteById(id);
            return true;
        } else {
            log.error("Inside deleteBankItem of BankGrpcServiceImpl: Bank doesn't exist");
        }
        return false;
    }

    @Override
    public void checkIfBankItemIsValidate(BankItem bankItem) {
        log.info("Inside checkIfBankItemIsValidate of BankGrpcServiceImpl");
        BankCheck bankCheck = BankFactory.getBankCheck(bankItem.getBankName());
        bankCheck.check(this.convertToBank(bankItem));
    }

    public Bank convertToBank(BankItem bankItem) {
        return new Bank(
                bankItem.getId(),
                bankItem.getBankName(),
                bankItem.getCode(),
                bankItem.getHolderName()
        );
    }
}
