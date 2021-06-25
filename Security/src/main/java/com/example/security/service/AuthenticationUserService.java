package com.example.security.service;

import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationUserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ---- UserDetailsService ----
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " is not found"));
        return user;
    }
    // ---- UserDetailsService ----
}