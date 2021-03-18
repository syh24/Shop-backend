package com.shop.mew.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.mew.domain.user.User;
import com.shop.mew.domain.user.UserRepository;
import com.shop.mew.service.UserService;
import com.shop.mew.web.dto.UserRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class UserControllerTest {

    @LocalServerPort private int port;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    private UserRepository userRepository;

    //MockMvc 설정
    //Spring security 적용
    @BeforeEach
    public void before() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
/*    @AfterEach
    public void clear(){
        userRepository.deleteAll();
    }*/
    /**
     * 회원가입 테스트
     */
    @Test
    void 회원가입() throws Exception {

        //given
        UserRequestDto userRequestDto = userValidJoinRequest();
        String url = "http://localhost:" + port + "/api/v1/user";
        //when
        MvcResult result = mvc.perform(post(url)
                .contentType(APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userRequestDto)))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result.getResponse().getContentAsString()).isEqualTo("회원 가입 성공");
    }

    @Test
    void 회원가입_실패() throws Exception {

        //given
        UserRequestDto userRequestDto = userInValidJoinRequest();
        String url = "http://localhost:" + port + "/api/v1/user";
        //when
        MvcResult result = mvc.perform(post(url)
                .contentType(APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userRequestDto)))
                .andExpect(status().is4xxClientError())
                .andReturn();
    }

    @Test
    void 회원중복_테스트 () throws Exception{
        UserRequestDto user1 = userValidJoinRequest();
        UserRequestDto user2 = userValidJoinRequest();
        String url = "http://localhost:" + port + "/api/v1/user";
        //when
        MvcResult result1 = mvc.perform(post(url)
                .contentType(APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user1)))
                .andExpect(status().isOk())
                .andReturn();

        assertThat(result1.getResponse().getContentAsString()).isEqualTo("회원 가입 성공");

        MvcResult result2 = mvc.perform(post(url)
                .contentType(APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user2)))
                .andExpect(status().is5xxServerError())
                .andReturn();
    }


    public UserRequestDto userValidJoinRequest() {
        return UserRequestDto.builder()
                .name("Test")
                .email("Test@naver.com")
                .password("1234")
                .build();
    }

    public UserRequestDto userInValidJoinRequest() {
        return UserRequestDto.builder()
                .name("Test")
                .email("Test@naver.com")
                .password("")
                .build();
    }
}