package com.shop.mew.service;

import com.shop.mew.domain.user.Role;
import com.shop.mew.domain.user.User;
import com.shop.mew.web.dto.UserRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserServiceTest {

    @Autowired
    private UserService userService;

    private String name;

    private String email;

    private String password;

    private String address;

    @BeforeAll
    void setUp() {
        name = "test";
        email = "test@gmail.com";
        password = "test123123";
        address = "Seoul, Korea";
    }

    @Test
    @Order(1)
    @DisplayName("사용자_회원가입")
    void test1() {
        User user = userService.join(UserRequestDto.builder()
                .name(name).email(email).password(password)
                .address(address).build());
        assertThat(user).isNotNull();
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getAddress()).isEqualTo(address);
        assertThat(user.getRole()).isEqualTo(Role.USER);
        log.info("User: {}", user);
    }

    @Test
    @Order(2)
    @DisplayName("사용자_로그인")
    void test2() {
        User user = userService.login(email, password);
        assertThat(user).isNotNull();
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getName()).contains(name);
        assertThat(user.getAddress()).contains(address);
        log.info("Login User: {}", user);
    }

    @Test
    @Order(3)
    @DisplayName("이메일로_사용자_조회")
    void test3() {
        User user = userService.findByEmail(email).orElse(null);
        assertThat(user).isNotNull();
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getEmail()).isEqualTo(email);
        assertThat(user.getName()).isEqualTo(name);
        assertThat(user.getAddress()).isEqualTo(address);
        log.info("Found by {}: {}", email, user);
    }
}