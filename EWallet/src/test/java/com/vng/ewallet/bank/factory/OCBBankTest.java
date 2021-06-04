package com.vng.ewallet.bank.factory;

import com.vng.ewallet.entity.Bank;
import com.vng.ewallet.exception.ApiRequestException;
import com.vng.ewallet.factory.bank.OCBBank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class OCBBankTest {
    @InjectMocks
    private OCBBank underTest;

    @Test
    void checkValidHolderName() {
        // given
        String holderName = "NGUYEN TRINH AN";

        // when

        // then
        assertThat(underTest.checkHolderName(holderName)).isEqualTo(true);
    }

    @Test
    void checkUnValidHolderName() {
        // given
        String holderName = "NGUYEN TRINH An";

        // when

        // then
        assertThat(underTest.checkHolderName(holderName)).isEqualTo(false);
    }

    @Test
    void checkValidCode() {
        // given
        String code = "0135557879";

        // when

        // then
        assertThat(underTest.checkCode(code)).isEqualTo(true);
    }

    @Test
    void checkUnValidCode() {
        // given
        String code1 = "0125557879";
        String code2 = "0135557878";
        // when

        // then
        assertThat(underTest.checkCode(code1)).isEqualTo(false);
        assertThat(underTest.checkCode(code2)).isEqualTo(false);
    }

    @Test
    void canCheckInValidCodeBank(){
        // given
        Bank bank = new Bank(1L, "VCB", "0125557879", "NGUYEN TRINH AN");

        // when

        // then
        assertThatThrownBy(() -> underTest.check(bank))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("Invalid bank code");
    }

    @Test
    void canCheckInValidHolderNameBank(){
        // given
        Bank bank = new Bank(1L, "VCB", "0135557879", "NGUYEN TRINH aN");

        // when

        // then
        assertThatThrownBy(() -> underTest.check(bank))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("Invalid bank holder name");
    }


}