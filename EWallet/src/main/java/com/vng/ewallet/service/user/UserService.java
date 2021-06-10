package com.vng.ewallet.service.user;

import com.vng.ewallet.entity.Bank;
import com.vng.ewallet.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAllUsers();

    User insertUser(User user);

    User updateUser(Long id, User user);

    boolean deleteUser(Long id);

    User findUserById(Long id);

    List<Bank> findAllBanks(Long id);

    User linkBank(Long id, Bank bank);
}
