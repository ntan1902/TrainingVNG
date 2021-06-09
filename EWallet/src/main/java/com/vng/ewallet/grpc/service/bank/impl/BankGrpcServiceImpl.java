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


@Service
@Log4j2
@RequiredArgsConstructor
public class BankGrpcServiceImpl implements BankGrpcService {
    private final BankRepository bankRepository;


    @Override
    public boolean findBankItemByCode(String code) {
        // TODO
        return false;
    }

    @Override
    public BankItem insertBankItem(BankItem bankItem) throws ApiRequestException{
        log.info("Inside insertBank of BankService");

        checkIfBankItemIsValidate(bankItem);

        this.bankRepository.save(this.convertToBank(bankItem));

        return bankItem;
    }


    @Override
    public BankItem updateBankItem(BankItem bankItem) {
        // TODO
        return null;
    }

    @Override
    public boolean deleteBankItem(Long id) {
        // TODO
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
