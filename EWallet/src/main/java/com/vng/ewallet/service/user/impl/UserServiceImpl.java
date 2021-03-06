package com.vng.ewallet.service.user.impl;

import com.vng.ewallet.entity.Bank;
import com.vng.ewallet.service.bank.BankService;
import com.vng.ewallet.entity.Card;
import com.vng.ewallet.service.card.CardService;
import com.vng.ewallet.service.card.impl.CardServiceImpl;
import com.vng.ewallet.exception.ApiRequestException;
import com.vng.ewallet.service.user.UserService;
import com.vng.ewallet.entity.User;
import com.vng.ewallet.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Hibernate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final CardService cardService;
    private final BankService bankService;

    @Transactional
    @Override
    public List<User> findAllUsers() {
        log.info("Inside findAllUsers of UserService");

        //        users.forEach(user -> Hibernate.initialize(user.getBanks()));

        return this.userRepository.findAll();
    }

    @CachePut(value = "users", key = "#id")
    @Override
    public User insertUser(User user) throws ApiRequestException {
        log.info("Inside insertUser of UserService");
        checkCard(user.getCard());
        checkBanks(user.getBanks());
        return this.userRepository.save(user);
    }

    public void checkBanks(Set<Bank> banks) throws ApiRequestException {
        log.info("Inside checkBanks of UserService");
        if (banks != null) {
            banks.forEach(bankService::checkIfBankIsValidate);
        }
    }

    public void checkCard(Card card) throws ApiRequestException {
        log.info("Inside checkCard of UserService");
        if (card != null) {
            this.cardService.checkIfCardIsValidate(card);
        } else {
            log.warn("Inside checkCard of UserService: Card is null");
        }
    }

    @CachePut(value = "users", key = "#id")
    @Override
    public User updateUser(Long id, User user) throws ApiRequestException {
        log.info("Inside updateUser of UserService");
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            // Set id for user
            user.setId(optionalUser.get().getId());

            checkCard(user.getCard());
            checkBanks(user.getBanks());
            return this.userRepository.save(user);
        } else {
            log.error("Inside updateUser of UserService: User doesn't exist");
            throw new ApiRequestException("User doesn't exist");
        }
    }

    @CacheEvict(value = "users", key = "#id", allEntries = false)
    @Override
    public boolean deleteUser(Long id) throws ApiRequestException {
        log.info("Inside deleteUser of UserService");
        if (this.userRepository.existsById(id)) {
            this.userRepository.deleteById(id);
            return true;
        } else {
            log.error("Inside deleteUser of UserService: User doesn't exist");
            throw new ApiRequestException("User doesn't exist");
        }
    }

    @Cacheable(value = "users", key = "#id")
    @Override
    public User findUserById(Long id) {
        log.info("Inside findUserById of UserService");

        User user = this.userRepository.findById(id)
                .orElse(null);
        log.info("Fetch from table User");

        return user;
    }

    @Override
    public Set<Bank> findAllBanks(Long id) throws ApiRequestException {
        log.info("Inside findAllBanks of UserService");
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.map(User::getBanks).orElse(null);
        } else {
            log.error("Inside findAllBanks of UserService: User doesn't exist");
            throw new ApiRequestException("User doesn't exist");
        }
    }

    @CachePut(value = "users", key = "#id")
    @Override
    public User linkBank(Long id, Bank bank) throws ApiRequestException {
        log.info("Inside linkBank of UserService");
        Optional<User> optionalUser = this.userRepository.findById(id);

        try {
            // Check if User exists
            if (optionalUser.isEmpty()) {
                throw new ApiRequestException("User doesn't exist");
            }

            // Check if bank exists in another account of user.
            if (this.bankService.findBankByCode(bank.getCode())) {
                throw new ApiRequestException("Bank already exists");
            }

            // Check if bank is validated
//            bankService.insertBank(bank);
            bankService.checkIfBankIsValidate(bank);
        } catch (ApiRequestException e) {
            log.error(e.getMessage());
            throw e;
        }

        // Find User
        User user = optionalUser.get();
        log.debug("Bank is checked validated");

        // Insert into bank table.
        // If success, then save bank into user account.
        Set<Bank> banks = user.getBanks();
        if (banks == null) {
            banks = new HashSet<>();
        }
        banks.add(bank);
        user.setBanks(banks);

        return this.userRepository.save(user);
    }
}
