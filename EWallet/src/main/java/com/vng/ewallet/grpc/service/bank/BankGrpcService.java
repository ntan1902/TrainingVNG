package com.vng.ewallet.grpc.service.bank;


import grpc.bank.BankItem;

public interface BankGrpcService {
    boolean findBankItemByCode(String code);

    BankItem insertBankItem(BankItem bankItem);

    BankItem updateBankItem(BankItem bankItem);

    boolean deleteBankItem(Long id);

    void checkIfBankItemIsValidate(BankItem bankItem);
}
