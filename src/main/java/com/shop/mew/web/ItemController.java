package com.shop.mew.web;

import com.shop.mew.service.ItemService;
import com.shop.mew.web.dto.ItemRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/item")
    public ResponseEntity<?> addItem(@Valid @RequestBody ItemRequestDto.Register itemRequestDto) {
        itemService.addItem(itemRequestDto);
        return ResponseEntity.ok("상품 등록 완료");
    }

}
