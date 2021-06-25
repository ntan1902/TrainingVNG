package com.example.security.repository;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository {

    Optional<UserDetails> findByUsername(String username);

    Optional<UserDetails> insert(UserDetails user);
}
