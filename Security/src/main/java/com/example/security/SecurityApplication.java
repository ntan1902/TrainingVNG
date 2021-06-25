package com.example.security;

import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class SecurityApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SecurityApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        UserDetails ntanUser = User.builder()
                .username("ntan")
                .password(passwordEncoder.encode("123456"))
                .roles("USER")
                .build();
        UserDetails htthanhUser = User.builder()
                .username("htthanh")
                .password(passwordEncoder.encode("123456"))
                .roles("USER")
                .build();
        UserDetails dbtienUser = User.builder()
                .username("dbtien")
                .password(passwordEncoder.encode("123456"))
                .roles("USER")
                .build();
        UserDetails ohnguyenUser = User.builder()
                .username("ohnguyen")
                .password(passwordEncoder.encode("123456"))
                .roles("USER")
                .build();

        System.out.println(userRepository.insert(ntanUser));
        System.out.println(userRepository.insert(htthanhUser));
        System.out.println(userRepository.insert(dbtienUser));
        System.out.println(userRepository.insert(ohnguyenUser));
    }
}
