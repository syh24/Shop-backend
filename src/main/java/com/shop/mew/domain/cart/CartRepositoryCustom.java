package com.shop.mew.domain.cart;

import java.util.Optional;

public interface CartRepositoryCustom {

    Optional<Cart> findCartByUserAndItem(Long userId, Long itemId);
}
