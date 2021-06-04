package com.vng.ewallet.service.user;

import com.vng.ewallet.entity.Bank;
import com.vng.ewallet.entity.User;

import java.util.List;

public interface UserService {
    public List<User> findAllUsers();

    public User insertUser(User user);

    public User updateUser(Long id, User user);

    public boolean deleteUser(Long id);

    public User findUserById(Long id);

    public List<Bank> findAllBanks(Long id);

    public User linkBank(Long id, Bank bank);
}
