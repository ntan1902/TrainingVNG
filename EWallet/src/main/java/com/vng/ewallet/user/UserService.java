package com.vng.ewallet.user;

import com.vng.ewallet.bank.Bank;
import com.vng.ewallet.bank.BankService;
import com.vng.ewallet.card.Card;
import com.vng.ewallet.card.CardService;
import com.vng.ewallet.dto.BankUser;
import com.vng.ewallet.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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


    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    public User insertUser(User user) {
        checkCard(user.getCard());
        checkBanks(user.getBanks());
        return this.userRepository.save(user);
    }

    private void checkBanks(List<Bank> banks) {
        if(banks != null) {
            banks.forEach(bankService::checkIfBankIsValidate);
        }
    }

    private void checkCard(Card card) {
        if(card!= null) {
            this.cardService.checkIfCardIsValidate(card);
        }
    }

    public User updateUser(Long id, User user) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if(optionalUser.isPresent()) {
            // Set id for user
            user.setId(optionalUser.get().getId());

            checkCard(user.getCard());
            checkBanks(user.getBanks());
            return this.userRepository.save(user);
        }
        return null;
    }

    public boolean deleteUser(Long id) {
        if(this.userRepository.existsById(id)) {
            this.userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public User findUserById(Long id) {
        return this.userRepository
                .findById(id)
                .orElse(null);
    }

    public List<Bank> findBanks(Long id) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        return optionalUser.map(User::getBanks).orElse(null);
    }

    public Bank linkBank(Long id, Bank bank) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if(optionalUser.isPresent()){
            // Find User
            User user = optionalUser.get();

            // Check if bank exists in another account of user.
            if(this.bankService.findBank(bank)) {
                throw new ApiRequestException("Bank already exists.");
            }

            // Insert into bank table.
            this.bankService.insertBank(bank);

            // If success, then save bank into user account.
            user.getBanks().add(bank);
            this.userRepository.save(user);
            return bank;

        } else {
            throw new ApiRequestException("User doesn't exist");
        }
    }
}
