package com.shop.mew.domain.cart;

import java.util.Optional;

public interface CartRepositoryCustom {

    Optional<Cart> findCartByUser(Long id);
}
