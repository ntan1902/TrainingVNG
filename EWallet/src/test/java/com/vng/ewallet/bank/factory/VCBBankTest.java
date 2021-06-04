package com.vng.ewallet.bank.factory;

import com.vng.ewallet.entity.Bank;
import com.vng.ewallet.exception.ApiRequestException;
import com.vng.ewallet.factory.bank.VCBBank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;


@ExtendWith(MockitoExtension.class)
class VCBBankTest {
    @InjectMocks
    private VCBBank underTest;

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
        String code = "9704366614626076016";

        // when

        // then
        assertThat(underTest.checkCode(code)).isEqualTo(true);
    }

    @Test
    void checkUnValidCode() {
        // given
        String code1 = "111119704366614626076016";
        String code2 = "9704376614626076016";
        // when

        // then
        assertThat(underTest.checkCode(code1)).isEqualTo(false);
        assertThat(underTest.checkCode(code2)).isEqualTo(false);
    }

    @Test
    void canCheckInValidCodeBank(){
        // given
        Bank bank = new Bank(1L, "VCB", "111119704366614626076016", "NGUYEN TRINH AN");

        // when

        // then
        assertThatThrownBy(() -> underTest.check(bank))
            .isInstanceOf(ApiRequestException.class)
            .hasMessageContaining("Invalid bank code");
    }

    @Test
    void canCheckInValidHolderNameBank(){
        // given
        Bank bank = new Bank(1L, "VCB", "9704366614626076016", "NGUYEN TRINH aN");

        // when

        // then
        assertThatThrownBy(() -> underTest.check(bank))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("Invalid bank holder name");
    }
}