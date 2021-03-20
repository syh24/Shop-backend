package com.shop.mew.web;

import com.shop.mew.service.CartService;
import com.shop.mew.web.dto.CartRequestDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@Api(tags = "찜 목록 APIs")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class CartController {

    private final CartService cartService;

    @ApiOperation(value = "찜 목록 추가")
    @PostMapping("/cart")
    public ResponseEntity<String> getCartList(@Valid @RequestBody CartRequestDto cartRequestDto) {
        try {
            cartService.createCart(cartRequestDto);
        } catch (Exception e) {
            return new ResponseEntity<>("잘못된 값입니다.", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok("장바구니에 추가되었습니다.");
    }

    @ApiOperation(value = "찜 목록 조회")
    @PostMapping("/user/{userId}/cart")
    public HashMap<String, Object> getCartList(@PathVariable Long userId) {
        return cartService.userCartList(userId);
    }

    @ApiOperation(value = "찜 목록 삭제")
    @DeleteMapping ("/cart/{id}")
    public ResponseEntity<String> removeCart(@PathVariable Long id) {
        cartService.removeCart(id);
        return ResponseEntity.ok("삭제 되었습니다.");
    }

}
