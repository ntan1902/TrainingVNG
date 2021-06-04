package com.vng.ewallet.user;

import com.vng.ewallet.entity.Bank;
import com.vng.ewallet.entity.User;
import com.vng.ewallet.entity.UserRepository;
import com.vng.ewallet.service.bank.impl.BankServiceImpl;
import com.vng.ewallet.entity.Card;
import com.vng.ewallet.service.card.impl.CardServiceImpl;
import com.vng.ewallet.exception.ApiRequestException;
import com.vng.ewallet.service.user.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserServiceImpl underTest;

    @Mock
    private UserRepository userRepository;
    @Mock
    private CardServiceImpl cardService;
    @Mock
    private BankServiceImpl bankService;

    @Test
    void canFindAllUsers() {
        // when
        underTest.findAllUsers();

        // then
        verify(userRepository).findAll();
    }

    @Test
    void canInsertUser() {
        // given
        User user = new User (
                1L,
                "Nguyen Trinh An",
                "0915422217",
                Collections.singletonList(
                        new Bank(
                                1L,
                                "VCB",
                                "9704366614626076016",
                                "NGUYEN TRINH AN"
                        )
                ),
                new Card(
                        1L,
                        "CMND",
                        "026031189"
                )
        );
        // when
        underTest.insertUser(user);
        // then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);

    }

    @Test
    void canInsertUserWithNullableBanks() {
        // given
        User user = new User (
                1L,
                "Nguyen Trinh An",
                "0915422217",
                null,
                new Card(
                        1L,
                        "CMND",
                        "026031189"
                )
        );
        // when
        underTest.insertUser(user);
        // then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);

    }

    @Test
    void canInsertUserWithNullableCard() {
        // given
        User user = new User (
                1L,
                "Nguyen Trinh An",
                "0915422217",
                Collections.singletonList(
                        new Bank(
                                1L,
                                "VCB",
                                "9704366614626076016",
                                "NGUYEN TRINH AN"
                        )
                ),
                null
        );
        // when
        underTest.insertUser(user);
        // then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void canUpdateUser(){
        // given
        User user = new User (
                1L,
                "Nguyen Trinh An",
                "0915422217",
                Collections.singletonList(
                        new Bank(
                                1L,
                                "VCB",
                                "9704366614626076016",
                                "NGUYEN TRINH AN"
                        )
                ),
                new Card (
                        1L,
                        "CMND",
                        "026031189"
                )
        );
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));

        User updateUser = new User (
                1L,
                "On Hao Nguyen",
                "0915422217",
                Collections.singletonList(
                        new Bank(
                                1L,
                                "VCB",
                                "9704366614626076016",
                                "NGUYEN TRINH AN"
                        )
                ),
                new Card (
                        1L,
                        "CMND",
                        "026031189"
                )
        );

        // when
        underTest.updateUser(user.getId(), updateUser);

        // then
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(updateUser);
    }

    @Test
    void canNotUpdateNotExistUser(){
        // given
        User updateUser = new User (
                1L,
                "On Hao Nguyen",
                "0915422217",
                Collections.singletonList(
                        new Bank(
                                1L,
                                "VCB",
                                "9704366614626076016",
                                "NGUYEN TRINH AN"
                        )
                ),
                new Card (
                        1L,
                        "CMND",
                        "026031189"
                )
        );
        given(userRepository.findById(updateUser.getId())).willReturn(Optional.empty());

        // when

        // then
        assertThatThrownBy(() -> underTest.updateUser(updateUser.getId(), updateUser))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("User doesn't exist");

        verify(userRepository, never()).save(updateUser);
    }

    @Test
    void canFindUserById() {
        // given
        User user = new User (
                1L,
                "Nguyen Trinh An",
                "0915422217",
                Collections.singletonList(
                        new Bank(
                                1L,
                                "VCB",
                                "9704366614626076016",
                                "NGUYEN TRINH AN"
                        )
                ),
                new Card (
                        1L,
                        "CMND",
                        "026031189"
                )
        );
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));

        // when
        User userById = underTest.findUserById(user.getId());

        // then
        verify(userRepository).findById(user.getId());
        assertThat(userById).isEqualTo(user);
    }

    @Test
    void canNotFindUserById() {
        // given
        given(userRepository.findById(1L)).willReturn(Optional.empty());

        // when
        User user = underTest.findUserById(1L);

        // then
        verify(userRepository).findById(1L);
        assertThat(user).isNull();
    }

    @Test
    void canDeleteUser(){
        // given
        User user = new User (
                1L,
                "Nguyen Trinh An",
                "0915422217",
                Collections.singletonList(
                        new Bank(
                                1L,
                                "VCB",
                                "9704366614626076016",
                                "NGUYEN TRINH AN"
                        )
                ),
                new Card (
                        1L,
                        "CMND",
                        "026031189"
                )
        );
        given(userRepository.existsById(user.getId())).willReturn(true);

        // when
        underTest.deleteUser(user.getId());

        // then
        verify(userRepository).deleteById(user.getId());
    }

    @Test
    void canNotDeleteNotExistUser(){
        // given
        given(userRepository.existsById(100L)).willReturn(false);

        // when

        // then
        assertThatThrownBy(() -> underTest.deleteUser(100L))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("User doesn't exist");
        verify(userRepository, never()).deleteById(100L);

    }
    @Test
    void canFindAllBanksOfUser() {
        // given
        User user = new User (
                1L,
                "Nguyen Trinh An",
                "0915422217",
                Collections.singletonList(
                        new Bank(
                                1L,
                                "VCB",
                                "9704366614626076016",
                                "NGUYEN TRINH AN"
                        )
                ),
                new Card(
                        1L,
                        "CMND",
                        "026031189"
                )
        );
        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));

        // when
        List<Bank> banks = underTest.findAllBanks(user.getId());

        // then
        assertThat(user.getBanks()).isEqualTo(banks);

    }
    @Test
    void canNotFindAllBanksOfNotExistUser() {
        // given

        // when

        // then
        assertThatThrownBy(() -> underTest.findAllBanks(1L))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("User doesn't exist");
    }

    @Test
    void canLinkBank() {
        // given
        List<Bank> banks = new ArrayList<>();
        banks.add(new Bank(
                1L,
                "VCB",
                "9704366614626076016",
                "NGUYEN TRINH AN"
        ));
        User user = new User (
                1L,
                "Nguyen Trinh An",
                "0915422217",
                banks,
                new Card(
                        1L,
                        "CMND",
                        "026031189"
                )
        );

        Bank newBank = new Bank(
                2L,
                "SCB",
                "950436661678",
                "NGUYEN TRINH AN"
        );

        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        given(bankService.findBankByCode(newBank.getCode())).willReturn(false);
//        given(bankService.insertBank(newBank)).willReturn(newBank);
//        given(user.getBanks().add(newBank)).willReturn(null);

        // when
        underTest.linkBank(user.getId(), newBank);

        // then
        verify(userRepository).save(user);
    }

    @Test
    void canNotLinkBankOfNotExistUser() {
        // given
        Bank newBank = new Bank(
                2L,
                "SCB",
                "950436661678",
                "NGUYEN TRINH AN"
        );
        // when

        // then
        assertThatThrownBy(() -> underTest.linkBank(1L, newBank))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("User doesn't exist");
    }

    @Test
    void canNotLinkExistedBank() {
        // given
        List<Bank> banks = new ArrayList<>();
        banks.add(new Bank(
                1L,
                "VCB",
                "9704366614626076016",
                "NGUYEN TRINH AN"
        ));

        User user = new User (
                1L,
                "Nguyen Trinh An",
                "0915422217",
                banks,
                new Card(
                        1L,
                        "CMND",
                        "026031189"
                )
        );

        Bank newBank = new Bank(
                2L,
                "VCB",
                "9704366614626076016",
                "NGUYEN TRINH AN"
        );

        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        given(bankService.findBankByCode(newBank.getCode())).willReturn(true);
        // when

        // then
        assertThatThrownBy(() -> underTest.linkBank(1L, newBank))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("Bank already exists");
    }

    @Test
    void canNotLinkInvalidBank() {
        // given
        User user = new User (
                1L,
                "Nguyen Trinh An",
                "0915422217",
                null,
                new Card(
                        1L,
                        "CMND",
                        "026031189"
                )
        );

        Bank newBank = new Bank(
                1L,
                "VCB",
                "111119704366614626076016",
                "NGUYEN TRINH AN"
        );

        given(userRepository.findById(user.getId())).willReturn(Optional.of(user));
        given(bankService.findBankByCode(newBank.getCode())).willReturn(false);
        // when
        User newUser = underTest.linkBank(1L, newBank);

        // then
        assertThat(newUser).isNotEqualTo(user);
    }
}