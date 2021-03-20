package com.shop.mew.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.mew.domain.user.Role;
import com.shop.mew.domain.user.User;
import com.shop.mew.service.UserService;
import com.shop.mew.web.dto.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.JsonPathAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@Transactional
@SpringBootTest
class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders
                .standaloneSetup(userController)
                .build();
    }

    @Test
    @DisplayName("로그인 테스트")
    void test1() throws Exception {
        User user = new User(1L, "test", "test@gmail.com", "test123123", "Seoul", Role.USER);
        LoginRequest request = new LoginRequest("test@gmail.com","test123123");
        given(userService.login(any(), any())).willReturn(user);
        final ResultActions actions =
                mvc.perform(post("/api/v1/user/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)));

        actions.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("이메일 중복 확인")
    void test2() throws Exception {
        Map<String, String> request = new HashMap<>();
        request.put("email", "test@gmail.com");
        given(userService.findByEmail(any())).willReturn(Optional.empty());
        final ResultActions actions = mvc.perform(post("/api/v1/user/exists")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)));

        MvcResult mvcResult = actions.andDo(print())
                .andExpect(status().isOk()).andReturn();
        assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo("false");
    }
}