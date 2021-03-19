package com.shop.mew.web;

import com.shop.mew.domain.cart.Cart;
import com.shop.mew.service.CartService;
import com.shop.mew.web.dto.CartRequestDto;
import com.shop.mew.web.dto.CartResponseDto;
import com.shop.mew.web.dto.ItemResponseDto;
import com.shop.mew.web.dto.UserResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

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
        return ResponseEntity.ok().body("장바구니에 추가되었습니다.");
    }

    @ApiOperation(value = "찜 목록 조회")
    @PostMapping("/user/{userId}/cart")
    public HashMap<String, Object> getCartList(@PathVariable Long userId) {
        return cartService.userCartList(userId);
    }
}
