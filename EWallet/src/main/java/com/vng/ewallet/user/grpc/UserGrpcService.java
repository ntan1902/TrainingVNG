package com.vng.ewallet.user.grpc;

import com.vng.ewallet.bank.Bank;
import com.vng.ewallet.card.Card;
import com.vng.ewallet.grpc.model.*;
import com.vng.ewallet.user.User;
import com.vng.ewallet.user.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserGrpcService {
    private final UserRepository userRepository;

    @Autowired
    public UserGrpcService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Transactional
    public AllUsersResponse findAllUsers(EmptyRequest request) {
        List<User> users = userRepository.findAll();
        List<UserItem> userItems = new ArrayList<>();


        users.forEach(user -> {
            Hibernate.initialize(user.getBanks());
            List<BankItem> bankItems = new ArrayList<>();

            user.getBanks().forEach(bank -> bankItems.add(BankItem
                    .newBuilder()
                    .setId(bank.getId())
                    .setBankName(bank.getBankName())
                    .setCode(bank.getCode())
                    .setHolderName(bank.getHolderName())
                    .build()
            ));

            Card card = user.getCard();
            CardItem cardItem = CardItem
                    .newBuilder()
                    .setId(card.getId())
                    .setCardName(card.getCardName())
                    .setCode(card.getCode())
                    .build();

            userItems.add(
                    UserItem
                            .newBuilder()
                            .setId(user.getId())
                            .setUserName(user.getUserName())
                            .setPhoneNumber(user.getPhoneNumber())
                            .addAllBanks(bankItems)
                            .setCard(cardItem)
                            .build()
            );
        });

        return AllUsersResponse
                .newBuilder()
                .addAllUsers(userItems)
                .build();


    }
}
