package com.shop.mew.service;

import com.shop.mew.domain.item.Item;
import com.shop.mew.web.dto.ItemRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    private String name;

    private String category;

    private Integer price;

    private Integer count;

    private String img;

    @BeforeAll
    void setUp() {
        name = "testItem";
        category = "Book";
        price = 10000;
        count = 100;
        img = "test.jpg";
    }

    @Test
    @Order(1)
    @DisplayName("상품_추가")
    void test1() throws Exception {
        itemService.addItem(ItemRequestDto.Register.builder()
                .name(name).category(category).price(price)
                .count(count).img(img).build());
        Item item = itemService.findAll().get(0);
        assertThat(item).isNotNull();
        assertThat(item.getName()).isEqualTo(name);
        assertThat(item.getCategory()).isEqualTo(category);
        assertThat(item.getPrice()).isEqualTo(price);
        assertThat(item.getCount()).isEqualTo(count);
        assertThat(item.getImg()).isEqualTo(img);

        log.info("Item: {}", item);
    }

    @Test
    @Order(2)
    @DisplayName("상품_업데이트")
    void test2() throws Exception {
        itemService.updateItem(1L, ItemRequestDto.Update
                .builder().count(200).price(20000).build());
        Item item = itemService.findAll().get(0);
        assertThat(item).isNotNull();
        assertThat(item.getPrice()).isEqualTo(20000);
        assertThat(item.getCount()).isEqualTo(200);

        log.info("Item: {}", item);
    }
}