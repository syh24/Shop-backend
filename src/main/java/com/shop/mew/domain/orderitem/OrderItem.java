package com.shop.mew.domain.orderitem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.shop.mew.domain.BaseTimeEntity;
import com.shop.mew.domain.cart.Cart;
import com.shop.mew.domain.enums.OrderStatus;
import com.shop.mew.domain.user.User;
import com.shop.mew.web.dto.OrderItemResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "orderItem")
    @JsonIgnore
    private List<Cart> carts;

    public OrderItemResponseDto toResponseDto() {
        LocalDateTime createdDate = this.getModifiedDate();
        return OrderItemResponseDto.builder()
                .id(id)
                .orderNumber(orderNumber)
                .orderName(orderName)
                .deliveryMessage(message)
                .orderStatus(orderStatus)
                .amount(amount)
                .createdDate(createdDate.getYear() + "." + createdDate.getMonthValue() + "."
                        + createdDate.getDayOfMonth() + " " + createdDate.getHour() + ":" + createdDate.getMinute() + ":"
                        + createdDate.getSecond())
                .carts(carts)
                .build();
    }
}
