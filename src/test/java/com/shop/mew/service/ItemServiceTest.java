package com.shop.mew.service;

import com.shop.mew.domain.item.Item;
import com.shop.mew.web.dto.ItemRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Test
    void 상품추가 () throws Exception {
        ItemRequestDto.Register item = ItemRequestDto.Register.builder()
                .name("test")
                .category("test")
                .img("test")
                .count(1)
                .price(10000)
                .build();

        itemService.addItem(item);
        List<Item> result = itemService.findAll();
        for (Item item1 : result) {
            System.out.println("item1 = " + item1.getPrice());
        }
    }
}