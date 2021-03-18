package com.shop.mew.service;

import com.shop.mew.domain.cart.Cart;
import com.shop.mew.domain.cart.CartRepository;
import com.shop.mew.domain.item.Item;
import com.shop.mew.domain.item.ItemRepository;
import com.shop.mew.domain.user.User;
import com.shop.mew.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CartService {

    private final ItemRepository itemRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public void addCart(Long userId, Long itemId, Integer count) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
        cartRepository.save(createCart(user, item, count));
    }

    private Cart createCart(User user, Item item, Integer count) {
        Cart cart = new Cart();
        cart.addUser(user);
        cart.addItem(item);
        cart.orderCount(count);
        return cart;
    }
}
