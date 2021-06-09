package com.vng.ewallet.grpc.service.user.impl;

import com.google.protobuf.Empty;
import com.vng.ewallet.entity.Bank;
import com.vng.ewallet.entity.Card;
import com.vng.ewallet.entity.User;
import com.vng.ewallet.entity.UserRepository;
import com.vng.ewallet.exception.ApiRequestException;
import com.vng.ewallet.grpc.service.bank.BankGrpcService;
import com.vng.ewallet.grpc.service.card.CardGrpcService;
import grpc.bank.BankItem;
import grpc.card.CardItem;
import grpc.user.APIResponse;
import grpc.user.UserIdRequest;
import grpc.user.UserItem;
import grpc.user.UserItemsResponse;
import com.vng.ewallet.grpc.service.user.UserGrpcService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserGrpcServiceImpl implements UserGrpcService {
    private final UserRepository userRepository;
    private final BankGrpcService bankGrpcService;
    private final CardGrpcService cardGrpcService;

    @Transactional
    @Override
    public UserItemsResponse findAllUserItems(Empty request) {
        log.info("Inside findAllUsers of UserGrpcService");
        List<User> users = userRepository.findAll();

        List<UserItem> userItems = new LinkedList<>();
        users.forEach(user -> userItems.add(this.convertToUserItem(user)));

        return UserItemsResponse
                .newBuilder()
                .addAllUsers(userItems)
                .build();
    }

    @Override
    public UserItem insertUserItemById(UserItem request) {
        log.info("Inside insertUserById of UserGrpcServiceImpl");
        checkCard(request.getCard());
        checkBanks(request.getBanksList());
        this.userRepository.save(this.convertToUser(request));
        return request;
    }

    @Transactional
    public UserItem findUserItemById(UserIdRequest request) {
        User user = this.userRepository
                .findById((long) request.getId())
                .get();

        return convertToUserItem(user);
    }



    @Override
    public UserItem updateUserItem(UserItem request) {
        Optional<User> optionalUser = this.userRepository.findById(request.getId());
        if(optionalUser.isPresent()) {
            checkCard(request.getCard());
            checkBanks(request.getBanksList());

            this.userRepository.save(this.convertToUser(request));
            return request;
        } else {
            log.error("Inside updateUser of UserGrpcServiceImpl: User doesn't exist");
            throw new ApiRequestException("User doesn't exist");
        }
    }

    private void checkBanks(List<BankItem> banksList) {
        log.info("Inside checkBanks of UserGrpcServiceImpl");
        if (banksList != null) {
            banksList.forEach(bankGrpcService::checkIfBankItemIsValidate);
        }
    }

    private void checkCard(CardItem card) {
        log.info("Inside checkCard of UserGrpcServiceImpl");
        if (card != null) {
            this.cardGrpcService.checkIfCardItemIsValidate(card);
        } else {
            log.warn("Inside checkCard of UserGrpcServiceImpl: Card is null");
        }
    }

    @Override
    public APIResponse deleteUserItemById(UserIdRequest request) {
        // TODO
        return null;
    }

    private UserItem convertToUserItem(User user) {
        // Mapping Bank into BankItem
        List<BankItem> bankItems = new LinkedList<>();

        Hibernate.initialize(user.getBanks());
        user.getBanks().forEach(bank -> {
            bankItems.add(
                    BankItem
                            .newBuilder()
                            .setId(bank.getId())
                            .setCode(bank.getCode())
                            .setBankName(bank.getBankName())
                            .setHolderName(bank.getHolderName())
                            .build()
            );
        });

        // Mapping Card into CardItem
        Card card = user.getCard();
        CardItem cardItem = CardItem
                .newBuilder()
                .setId(card.getId())
                .setCardName(card.getCardName())
                .setCode(card.getCode())
                .build();

        return UserItem
                .newBuilder()
                .setId(user.getId())
                .setUserName(user.getUserName())
                .setPhoneNumber(user.getPhoneNumber())
                .addAllBanks(bankItems)
                .setCard(cardItem)
                .build();
    }

    public User convertToUser(UserItem userItem){
        // Mapping BankItem into Bank
        List<Bank> banks = new LinkedList<>();

        userItem.getBanksList().forEach(bankItem -> {
            banks.add(new Bank(
                    bankItem.getId(),
                    bankItem.getBankName(),
                    bankItem.getCode(),
                    bankItem.getHolderName()
            ));
        });

        // Mapping CardItem into Card
        CardItem cardItem = userItem.getCard();
        Card card = new Card(
                cardItem.getId(),
                cardItem.getCardName(),
                cardItem.getCode()
        );

        return new User (
                userItem.getId(),
                userItem.getUserName(),
                userItem.getPhoneNumber(),
                banks,
                card
        );
    }
}
