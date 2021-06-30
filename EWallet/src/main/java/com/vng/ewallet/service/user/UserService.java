package com.vng.ewallet.service.user;

import com.vng.ewallet.entity.Bank;
import com.vng.ewallet.entity.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<User> findAllUsers();

    User insertUser(User user);

    User updateUser(Long id, User user);

    boolean deleteUser(Long id);

    User findUserById(Long id);

    Set<Bank> findAllBanks(Long id);

    User linkBank(Long id, Bank bank);
}
