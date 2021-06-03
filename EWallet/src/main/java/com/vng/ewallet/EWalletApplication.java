package com.vng.ewallet;

import com.vng.ewallet.bank.Bank;
import com.vng.ewallet.bank.BankRepository;
import com.vng.ewallet.card.Card;
import com.vng.ewallet.user.User;
import com.vng.ewallet.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@SpringBootApplication
@EnableCaching
@RequiredArgsConstructor
public class EWalletApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(EWalletApplication.class, args);
    }

    private final UserRepository userRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        userRepository.save(
                new User(1L,
                        "ntan1902",
                        "091542217",
                        Collections.singletonList(
                                new Bank(1L, "VCB", "9704366614626076016", "NGUYEN TRINH AN")
                        ),
                        new Card(
                                1L,
                                "CMND",
                                "026031189"
                        )
                )
        );

        userRepository.save(
                new User(
                        2L,
                        "ohnguyen",
                        "0934645079",
                        Collections.singletonList(
                                new Bank(2L, "SCB", "950436661678", "ON HAO NGUYEN")
                        ),
                        new Card(
                                2L,
                                "CMND",
                                "026117869"
                        )
                )
        );
        userRepository.save(
                new User(
                        3L,
                        "nthao",
                        "089535789",
                        Collections.singletonList(
                                new Bank(3L, "OCB", "0135557879", "NGUYEN TAN HAO")
                        ),
                        new Card(
                                3L,
                                "CMND",
                                "026031189"
                        )
                )
        );

//        userRepository.findAll().forEach(System.out::println);
    }
}
