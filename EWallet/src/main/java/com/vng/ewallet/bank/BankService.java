package com.vng.ewallet.bank;

import com.vng.ewallet.bank.factory.BankFactory;
import com.vng.ewallet.bank.factory.BankCheck;
import com.vng.ewallet.exception.ApiRequestException;
import com.vng.ewallet.user.User;
import com.vng.ewallet.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankService {
    private final Logger logger = LoggerFactory.getLogger(BankService.class);
    private final BankRepository bankRepository;
    private final UserRepository userRepository;

    @Autowired
    public BankService(BankRepository bankRepository, UserRepository userRepository) {
        this.bankRepository = bankRepository;
        this.userRepository = userRepository;
    }

    public List<Bank> findAllBanks() {
        try {
            return this.bankRepository.findAll();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public Bank insertBank(Bank bank) {
        check(bank);
        return this.bankRepository.save(bank);

    }

    private void check(Bank bank) {
        checkIfUserIsExist(bank);

        checkIfBankIsValidate(bank);
    }

    private void checkIfUserIsExist(Bank bank) {
        Optional<User> optionalUser = userRepository.findById(bank.getUser().getId());

        if(optionalUser.isPresent()) {
            bank.setUser(optionalUser.get());
        } else {
            throw new ApiRequestException("User is not exist");
        }
    }

    private void checkIfBankIsValidate(Bank bank) {
        BankCheck bankCheck = BankFactory.getBankCheck(bank.getBankName());
        bankCheck.check(bank);
    }
}
