package com.vng.ewallet.user;

import com.vng.ewallet.bank.Bank;
import com.vng.ewallet.bank.BankService;
import com.vng.ewallet.card.Card;
import com.vng.ewallet.card.CardService;
import com.vng.ewallet.exception.ApiRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserService underTest;

    @Mock
    private UserRepository userRepository;
    @Mock
    private CardService cardService;
    @Mock
    private BankService bankService;

    @Test
    void canFindAllUsers() {
        // Exercise
        underTest.findAllUsers();

        // Verify
        verify(userRepository).findAll();
    }

    @Test
    void canInsertUser() {
        // Setup
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
        // Exercise
        underTest.insertUser(user);
        // Verify
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);

    }

    @Test
    void canInsertUserWithNullableBanks() {
        // Setup
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
        // Exercise
        underTest.insertUser(user);
        // Verify
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);

    }

    @Test
    void canInsertUserWithNullableCard() {
        // Setup
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
        // Exercise
        underTest.insertUser(user);
        // Verify
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();

        assertThat(capturedUser).isEqualTo(user);
    }

    @Test
    void canUpdateUser(){
        // Setup
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

        // Exercise
        underTest.updateUser(user.getId(), updateUser);

        // Verify
        ArgumentCaptor<User> userArgumentCaptor =
                ArgumentCaptor.forClass(User.class);

        verify(userRepository).save(userArgumentCaptor.capture());
        User capturedUser = userArgumentCaptor.getValue();
        assertThat(capturedUser).isEqualTo(updateUser);
    }

    @Test
    void canNotUpdateNotExistUser(){
        // Setup
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

        // Exercise

        // Verify
        assertThatThrownBy(() -> underTest.updateUser(updateUser.getId(), updateUser))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("User doesn't exist");

        verify(userRepository, never()).save(updateUser);
    }

    @Test
    void canFindUserById() {
        // Exercise
        underTest.findUserById(1L);

        // Verify
        verify(userRepository).findById(1L);
    }

    @Test
    void canDeleteUser(){
        // Setup
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

        // Exercise
        underTest.deleteUser(user.getId());

        // Verify
        verify(userRepository).deleteById(user.getId());
    }

    @Test
    void canNotDeleteNotExistUser(){
        // Setup
        given(userRepository.existsById(100L)).willReturn(false);

        // Exercise

        // Verify
        assertThatThrownBy(() -> underTest.deleteUser(100L))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("User doesn't exist");
        verify(userRepository, never()).deleteById(100L);

    }
    @Test
    void canFindBanksOfUser() {
        // Setup
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

        // Exercise
        List<Bank> banks = underTest.findBanks(user.getId());

        // Verify
        assertThat(user.getBanks()).isEqualTo(banks);

    }
    @Test
    void canNotFindBanksOfNotExistUser() {
        // Setup

        // Exercise

        // Verify
        assertThatThrownBy(() -> underTest.findBanks(1L))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("User doesn't exist");
    }

    @Test
    void canLinkBank() {
        // Setup
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
        given(bankService.findBank(newBank)).willReturn(false);
        given(bankService.insertBank(newBank)).willReturn(newBank);
//        given(user.getBanks().add(newBank)).willReturn(null);

        // Exercise
        underTest.linkBank(user.getId(), newBank);

        // Verify
        verify(userRepository).save(user);
    }

    @Test
    void canNotLinkBankOfNotExistUser() {
        // Setup
        Bank newBank = new Bank(
                2L,
                "SCB",
                "950436661678",
                "NGUYEN TRINH AN"
        );
        // Exercise

        // Verify
        assertThatThrownBy(() -> underTest.linkBank(1L, newBank))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("User doesn't exist");
    }

    @Test
    void canNotLinkExistedBank() {
        // Setup
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
        given(bankService.findBank(newBank)).willReturn(true);
        // Exercise

        // Verify
        assertThatThrownBy(() -> underTest.linkBank(1L, newBank))
                .isInstanceOf(ApiRequestException.class)
                .hasMessageContaining("Bank already exists");
    }
}