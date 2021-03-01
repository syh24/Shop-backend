package com.shop.mew.service;

import com.shop.mew.web.dto.ItemRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    @Commit
    void 상품추가 () throws Exception {
        ItemRequestDto.Register item = ItemRequestDto.Register.builder()
                .name("test")
                .category("test")
                .img("test")
                .count(1)
                .price(10000)
                .build();

        System.out.println("item = " + item);

        itemService.addItem(item);

        assertThat(itemService.findAll().get(0).getName()).isEqualTo("test");

    }


}