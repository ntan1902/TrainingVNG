package com.vng.ewallet.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    public User insertUser(User user) {
        return this.userRepository.save(user);
    }
}
