package com.shop.mew.domain.orderitem;

import com.shop.mew.domain.BaseTimeEntity;
import com.shop.mew.domain.cart.Cart;
import com.shop.mew.domain.enums.OrderStatus;
import com.shop.mew.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class OrderItem extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private Integer amount;

    private String message;

    private OrderStatus orderStatus;

    private String orderName;

    private String orderNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "orderItem")
    private List<Cart> carts;

}
