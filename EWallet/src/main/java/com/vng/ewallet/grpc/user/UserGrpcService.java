package com.vng.ewallet.grpc.user;

import com.google.protobuf.Empty;
import com.vng.ewallet.entity.Card;
import com.vng.ewallet.entity.User;
import com.vng.ewallet.entity.UserRepository;
import com.vng.ewallet.grpc.UserItem;
import com.vng.ewallet.grpc.UserItemsResponse;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
@Log4j2
public class UserGrpcService {
    private final UserRepository userRepository;

    @Autowired
    public UserGrpcService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserItemsResponse findAllUsers(Empty request) {
        log.info("Inside findAllUsers of UserGrpcService");
        List<User> users = userRepository.findAll();
        List<UserItem> userItems = new LinkedList<>();


        users.forEach(user -> {
            Hibernate.initialize(user.getBanks());
            List<UserItem.BankItem> bankItems = new LinkedList<>();

            user.getBanks().forEach(bank -> bankItems.add(UserItem.BankItem
                    .newBuilder()
                    .setId(bank.getId())
                    .setBankName(bank.getBankName())
                    .setCode(bank.getCode())
                    .setHolderName(bank.getHolderName())
                    .build()
            ));

            Card card = user.getCard();
            UserItem.CardItem cardItem = UserItem.CardItem
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

        return UserItemsResponse
                .newBuilder()
                .addAllUsers(userItems)
                .build();


    }
}
