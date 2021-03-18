package com.shop.mew.web;

import com.shop.mew.service.CartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "찜 목록 APIs")
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@RestController
public class CartController {

    private final CartService cartService;

/*    @ApiOperation(value = "찜 목록 추가")
    @PostMapping("/cart/add")
    public ResponseEntity<?> addCart(@RequestParam Long itemId,
                                     @RequestParam Integer count) {
        cartService.addCart(userId, itemId, count);
    }*/
}
