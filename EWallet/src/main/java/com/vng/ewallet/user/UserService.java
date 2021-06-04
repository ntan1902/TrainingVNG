package com.vng.ewallet.user;

import com.vng.ewallet.bank.Bank;
import com.vng.ewallet.bank.BankService;
import com.vng.ewallet.card.Card;
import com.vng.ewallet.card.CardService;
import com.vng.ewallet.exception.ApiRequestException;
import io.vertx.core.AbstractVerticle;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UserService {
    private final UserRepository userRepository;
    private final CardService cardService;
    private final BankService bankService;

    @Autowired
    public UserService(UserRepository userRepository, CardService cardService, BankService bankService) {
        this.userRepository = userRepository;
        this.cardService = cardService;
        this.bankService = bankService;
    }


    @Transactional
    public List<User> findAllUsers() {
        log.info("Inside findAllUsers of UserService");

        List<User> users = this.userRepository.findAll();
        Hibernate.initialize(users);
        users.forEach(user -> Hibernate.initialize(user.getBanks()));

        return users;
    }

    @CachePut(value = "users", key = "#id")
    public User insertUser(User user) {
        log.info("Inside insertUser of UserService");
        checkCard(user.getCard());
        checkBanks(user.getBanks());
        return this.userRepository.save(user);
    }

    public void checkBanks(List<Bank> banks) {
        log.info("Inside checkBanks of UserService");
        if (banks != null) {
            banks.forEach(bankService::checkIfBankIsValidate);
        }
    }

    public void checkCard(Card card) {
        log.info("Inside checkCard of UserService");
        if (card != null) {
            this.cardService.checkIfCardIsValidate(card);
        } else {
            log.warn("Inside checkCard of UserService: Card is null");
        }
    }

    @CachePut(value = "users", key = "#id")
    public User updateUser(Long id, User user) {
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
    public boolean deleteUser(Long id) {
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
    public User findUserById(Long id) {
        log.info("Inside findUserById of UserService");

        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            log.info("Fetch from table User");
            User user = optionalUser.get();

            // User this function of Hibernate to initialize proxy for FetchType.LAZY
            Hibernate.initialize(user.getBanks());
            return user;
        } else {
            return null;
        }
//        User user = (User) redisTemplate.opsForHash().get(KEY, id);
//        if(user != null) {
//            return user;
//        } else {
//            log.info("Fetch from table User");
//            Optional<User> optionalUser = this.userRepository.findById(id);
//            if(optionalUser.isPresent()) {
//                redisTemplate.opsForHash().put(KEY, id, optionalUser.get());
//                return optionalUser.get();
//            }
//        }
//        return null;
    }

    public List<Bank> findAllBanks(Long id) {
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
    public User linkBank(Long id, Bank bank) {
        log.info("Inside linkBank of UserService");
        Optional<User> optionalUser = this.userRepository.findById(id);
        if (optionalUser.isPresent()) {
            // Find User
            User user = optionalUser.get();

            // Check if bank exists in another account of user.
            if (this.bankService.findBank(bank)) {
                log.error("Inside linkBank of UserService: Bank already exists");
                throw new ApiRequestException("Bank already exists");
            }
            // Insert into bank table.
            // If success, then save bank into user account.
            user.getBanks().add(bank);
            this.userRepository.save(user);
            return user;

        } else {
            log.error("Inside linkBank of UserService: User doesn't exist");
            throw new ApiRequestException("User doesn't exist");
        }
    }
}
