package com.shop.mew.service;

import com.shop.mew.domain.cart.Cart;
import com.shop.mew.domain.cart.CartRepository;
import com.shop.mew.domain.item.Item;
import com.shop.mew.domain.item.ItemRepository;
import com.shop.mew.domain.user.User;
import com.shop.mew.domain.user.UserRepository;
import com.shop.mew.exception.NotExistCartException;
import com.shop.mew.exception.NotExistItemException;
import com.shop.mew.exception.NotExistUserException;
import com.shop.mew.web.dto.CartRequestDto;
import com.shop.mew.web.dto.CartResponseDto;
import com.shop.mew.web.dto.ItemResponseDto;
import com.shop.mew.web.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class CartService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createCart(CartRequestDto cartRequestDto) {
        User user = userRepository.findById(cartRequestDto.getUserId())
                .orElseThrow(() -> new NotExistUserException("존재하지 않는 유저입니다."));

        Item item = itemRepository.findById(cartRequestDto.getItemId())
                .orElseThrow(() -> new NotExistItemException("존재하지 않는 상품입니다."));

        if (item.getCount() < cartRequestDto.getCount()) {
            throw new NotExistItemException("재고가 없습니다.");
        }
        cartRepository.save(new Cart(user, item, cartRequestDto.getCount()));
    }

    @Transactional
    public HashMap<String, Object> userCartList(Long userId) {
        HashMap<String, Object> resultMap = new HashMap<>();
        List<Cart> cartList = cartRepository.findAllByUserId(userId);
        List<Long> cartIdList = new ArrayList<>();
        List<CartResponseDto> carts = new ArrayList<>();
        for (Cart cart : cartList) {
            carts.add(CartResponseDto.builder()
                    .id(cart.getId())
                    .user(new UserResponseDto(cart.getUser().getName(), cart.getUser().getEmail(), cart.getUser().getAddress()))
                    .item(new ItemResponseDto(cart.getItem().getName(), cart.getItem().getCategory(), cart.getItem().getPrice(), cart.getItem().getCount(), cart.getItem().getImg()))
                    .itemCount(cart.getCount()).build());
        }
        int totalPrice = 0;
        for (Cart cart : cartList) {
            cartIdList.add(cart.getId());
            totalPrice += cart.getCount() * cart.getItem().getPrice();
        }
        resultMap.put("cartList", carts);
        resultMap.put("cartIdList", cartIdList);
        resultMap.put("totalPrice", totalPrice);
        return resultMap;
    }

    @Transactional
    public void removeCart(Long id) {
        cartRepository.delete(cartRepository.findById(id)
                .orElseThrow(() -> new NotExistCartException("존재하지 않는 장바구니 입니다.")));
    }

}
