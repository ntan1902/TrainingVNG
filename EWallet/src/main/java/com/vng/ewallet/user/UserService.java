package com.vng.ewallet.user;

import com.vng.ewallet.dto.BankUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public User updateUser(Long id, User user) {
        Optional<User> optionalUser = this.userRepository.findById(id);
        if(optionalUser.isPresent()) {
            User oldUser = optionalUser.get();

            // Set id
            user.setId(oldUser.getId());

            // Update name
            if(user.getUserName() == null) {
                user.setUserName(oldUser.getUserName());
            }

            // Update phone number
            if(user.getPhoneNumber() == null) {
                user.setPhoneNumber(oldUser.getPhoneNumber());
            }

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
        Optional<User> optionalUser = this.userRepository.findById(id);
        return optionalUser.orElse(null);

    }

    public List<BankUser> findBanks(Long id) {
        return this.userRepository.findBanks(id);
    }
}
