package com.shop.mew.web;

import com.shop.mew.domain.item.Item;
import com.shop.mew.service.ItemService;
import com.shop.mew.web.dto.ItemRequestDto;
import com.shop.mew.web.dto.ItemResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "상품 APIs")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ItemController {

    private final ItemService itemService;

    @ApiOperation(value = "상품 등록")
    @PostMapping("/item/add")
    public ResponseEntity<ItemResponseDto> addItem(@RequestBody ItemRequestDto.Register itemRequestDto) {
        return ResponseEntity.ok(itemService.addItem(itemRequestDto));
    }

    @ApiOperation(value = "상품 조회")
    @GetMapping("/items")
    public List<ItemResponseDto> findItemAll(){
        return itemService.findAll().stream()
                .map(i -> new ItemResponseDto(i.getName(), i.getCategory(), i.getPrice(), i.getCount(), i.getImg()))
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "상품 상세")
    @GetMapping("/item/{itemId}")
    public ResponseEntity<ItemResponseDto> findOneItem(@PathVariable Long itemId) {
        Item findItem = itemService.findOne(itemId);
        return ResponseEntity.ok(new ItemResponseDto(findItem.getName(), findItem.getCategory(), findItem.getPrice(), findItem.getCount(), findItem.getImg()));
    }

    @ApiOperation(value = "상품 수정")
    @PutMapping("/item/{itemId}")
    public ResponseEntity<ItemResponseDto> updateItem(@PathVariable Long itemId, @RequestBody ItemRequestDto.Update itemRequestDto) {
        return ResponseEntity.ok(itemService.updateItem(itemId, itemRequestDto));
    }

    @ApiOperation(value = "상품 삭제")
    @DeleteMapping("/item/{itemId}")
    public ResponseEntity<Boolean> deleteItem (@PathVariable Long itemId){
        try {
            return ResponseEntity.ok(itemService.deleteItem(itemId));
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }
}
