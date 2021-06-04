package com.vng.ewallet.user;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.vng.ewallet.entity.Bank;
import com.vng.ewallet.entity.Card;
import com.vng.ewallet.entity.User;
import com.vng.ewallet.exception.ApiExceptionHandler;
import com.vng.ewallet.server.user.UserController;
import com.vng.ewallet.service.user.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController underTest;

    private JacksonTester<User> jsonUser;
    private JacksonTester<Bank> jsonBank;

    @BeforeEach
    public void setup(){
        JacksonTester.initFields(this, new ObjectMapper());

        mockMvc = MockMvcBuilders.standaloneSetup(underTest)
                .setControllerAdvice(new ApiExceptionHandler())
                .build();
    }

    @Test
    void canFindAllUsers() throws Exception {
        // given
        List<User> users = new ArrayList<>();
        users.add(new User(1L,
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
                ))
        );

        given(userService.findAllUsers()).willReturn(users);

        // when
        MockHttpServletResponse response = mockMvc.perform(
                get("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void canFindAllUsersWithEmpty() throws Exception {
        // given
        List<User> users = new ArrayList<>();

        given(userService.findAllUsers()).willReturn(users);

        // when
        MockHttpServletResponse response = mockMvc.perform(
                get("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();


        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void canFindUserById() throws Exception {
        // given
        User user = new User(1L, "AN", "", null, null);
        given(userService.findUserById(1L)).willReturn(user);

        // when
        MockHttpServletResponse response = mockMvc.perform(
                get("/api/v1/users/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        assertThat(response.getContentAsString()).isEqualTo(
                jsonUser.write(user).getJson()
        );
    }

    @Test
    void canNotFindUserById() throws Exception {
        // given

        // when
        MockHttpServletResponse response = mockMvc.perform(
                get("/api/v1/users/" + 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }

    @Test
    void canInsertUser() throws Exception {
        // given
        User user = new User(1L, "AN", "", null, null);
        given(userService.insertUser(user)).willReturn(user);

        // when
        MockHttpServletResponse response = mockMvc.perform(
                post("/api/v1/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonUser.write(user).getJson()))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        assertThat(response.getContentAsString()).isEqualTo(
                jsonUser.write(user).getJson()
        );
    }

    @Test
    void canNotInsertUser() throws Exception {
        // given
        User user = new User(1L, "", "", null, null);

        // when
        MockHttpServletResponse response = mockMvc.perform(
                post("/api/v1/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonUser.write(user).getJson()))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    void canUpdateUser() throws Exception {
        // given
        User user = new User(1L, "AN", "", null, null);
        given(userService.updateUser(user.getId(), user)).willReturn(user);

        // when
        MockHttpServletResponse response = mockMvc.perform(
                put("/api/v1/users/" + 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonUser.write(user).getJson()))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        assertThat(response.getContentAsString()).isEqualTo(
                jsonUser.write(user).getJson()
        );
    }

    @Test
    void canNotUpdateUser() throws Exception {
        // given
        User user = new User(1L, "", "", null, null);

        // when
        MockHttpServletResponse response = mockMvc.perform(
                put("/api/v1/users/" + 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonUser.write(user).getJson()))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    void canDeleteUser() throws Exception {
        // given
        User user = new User(1L, "AN", "", null, null);
        given(userService.deleteUser(user.getId())).willReturn(true);

        // when
        MockHttpServletResponse response = mockMvc.perform(
                delete("/api/v1/users/" + 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void canNotDeleteUser() throws Exception {
        // given
        User user = new User(1L, "", "", null, null);

        // when
        MockHttpServletResponse response = mockMvc.perform(
                delete("/api/v1/users/" + 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }

    @Test
    void canFindAllBanksOfUser() throws Exception {
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

        given(userService.findAllBanks(user.getId())).willReturn(banks);

        // when
        MockHttpServletResponse response = mockMvc.perform(
                get("/api/v1/users/" + 1L + "/banks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void canNotFindAllBanksOfUser() throws Exception {
        // given
        given(userService.findAllBanks(1L)).willReturn(null);

        // when
        MockHttpServletResponse response = mockMvc.perform(
                get("/api/v1/users/" + 1L + "/banks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void canLinkBank() throws Exception {
        // given
        Bank bank = new Bank(
                1L,
                "VCB",
                "9704366614626076016",
                "NGUYEN TRINH AN"
        );

        User user = new User(1L, "", "", Collections.singletonList(bank), null);
        given(userService.linkBank(1L, bank)).willReturn(user);

        // when
        MockHttpServletResponse response = mockMvc.perform(
                post("/api/v1/users/" + 1L + "/link-bank")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonBank.write(bank).getJson()))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        assertThat(response.getContentAsString()).isEqualTo(
                jsonUser.write(user).getJson()
        );
    }

    @Test
    void canNotLinkBank() throws Exception {
        // given
        Bank bank = new Bank(
                1L,
                "",
                "9704366614626076016",
                "NGUYEN TRINH AN"
        );

        // when
        MockHttpServletResponse response = mockMvc.perform(
                post("/api/v1/users/" + 1L + "/link-bank")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(jsonBank.write(bank).getJson()))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}