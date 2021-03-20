package com.shop.mew.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.mew.domain.item.Item;
import com.shop.mew.service.ItemService;
import com.shop.mew.web.dto.ItemRequestDto;
import com.shop.mew.web.dto.ItemResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@Transactional
@SpringBootTest
class ItemControllerTest {

    @InjectMocks
    private ItemController itemController;

    @Mock
    private ItemService itemService;

    private MockMvc mvc;

    @BeforeEach
    public void setUp() {
        mvc = MockMvcBuilders
                .standaloneSetup(itemController)
                .build();
    }

    @Test
    @DisplayName("상품 등록 테스트")
    void test1() throws Exception {
        ItemRequestDto.Register request = ItemRequestDto.Register.builder()
                .name("testItem")
                .category("Book")
                .count(100)
                .price(10000)
                .img("test.jpg").build();

        ItemResponseDto response = new ItemResponseDto("testItem", "Book", 10000, 100, "test.jpg");
        given(itemService.addItem(any())).willReturn(response);
        final ResultActions actions = mvc.perform(post("/api/v1/item/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(request)));

        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemName").value(response.getItemName()))
                .andExpect(jsonPath("$.category").value(response.getCategory()))
                .andExpect(jsonPath("$.price").value(response.getPrice()))
                .andExpect(jsonPath("$.count").value(response.getCount()))
                .andExpect(jsonPath("$.img").value(response.getImg()));
    }

    @Test
    @DisplayName("상품 상세 테스트")
    void test2() throws Exception {
        Item item = createItem();
        ItemResponseDto response = new ItemResponseDto("testItem", "Book", 10000, 100, "test.jpg");
        given(itemService.findOne(any())).willReturn(item);

        ResultActions actions = mvc.perform(get("/api/v1/item/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content(anyString()));

        actions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.itemName").value(response.getItemName()))
                .andExpect(jsonPath("$.category").value(response.getCategory()))
                .andExpect(jsonPath("$.price").value(response.getPrice()))
                .andExpect(jsonPath("$.count").value(response.getCount()))
                .andExpect(jsonPath("$.img").value(response.getImg()));
    }

    private Item createItem() {
        return Item.builder()
                .name("testItem")
                .category("Book")
                .price(10000)
                .count(100)
                .img("test.jpg")
                .build();
    }
}