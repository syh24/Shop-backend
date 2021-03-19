package com.shop.mew.domain.cart;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.mew.domain.user.QUser;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.shop.mew.domain.cart.QCart.*;
import static com.shop.mew.domain.user.QUser.*;

@RequiredArgsConstructor
public class CartRepositoryImpl implements CartRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Cart> findCartByUser(Long id) {
        return Optional.ofNullable(queryFactory
                .selectFrom(cart)
                .join(cart.user, user)
                .where(user.id.eq(id))
                .fetchOne());
    }

}
