package com.shop.mew.web;

import com.shop.mew.domain.item.Item;
import com.shop.mew.service.ItemService;
import com.shop.mew.web.dto.ItemRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/item")
    public ResponseEntity<?> addItem (@Valid @RequestBody ItemRequestDto.Register itemRequestDto){
        itemService.addItem(itemRequestDto);
        return ResponseEntity.ok("상품 등록 완료");
    }

    @PostMapping("/item/{id}")
    public Item updateItem (@PathVariable("id") Long id, @RequestBody ItemRequestDto.Update itemRequestDto){
        Item item = itemService.updateItem(id,itemRequestDto);
        return item;
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<?> deleteItem (@PathVariable("id") Long id){
        itemService.deleteItem(id);
        return ResponseEntity.ok("상품 삭제 완료");
    }
}
