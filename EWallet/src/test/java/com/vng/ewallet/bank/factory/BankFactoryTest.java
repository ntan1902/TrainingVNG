package com.vng.ewallet.bank.factory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class BankFactoryTest {
    @InjectMocks
    private BankFactory underTest;

    @Test
    void canInitialize() {
        // given
        Map<String, BankCheck> map = new HashMap<>();

        // when

        // then
    }
}