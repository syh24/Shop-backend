package com.shop.mew.domain.cart;

import com.shop.mew.domain.BaseTimeEntity;
import com.shop.mew.domain.item.Item;
import com.shop.mew.domain.orderitem.OrderItem;
import com.shop.mew.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Getter
@NoArgsConstructor
@Entity
public class Cart extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer count;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "orderitem_id")
    private OrderItem orderItem;

}
