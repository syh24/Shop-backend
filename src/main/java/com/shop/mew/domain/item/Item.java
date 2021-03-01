package com.shop.mew.domain.item;

import com.shop.mew.domain.BaseTimeEntity;
import com.shop.mew.domain.cart.Cart;
import com.shop.mew.domain.review.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Item extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    @Column(length = 50)
    private String name;

    private String category;

    private Integer price;

    @ColumnDefault("0")
    private Integer count;

    private String img;

    @OneToMany(mappedBy = "item")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<Cart> carts = new ArrayList<>();

    @ColumnDefault("0")
    private Integer rate; //평점

    @Builder
    public Item(Long id, String name,
                String category,
                Integer price,
                Integer count,
                String img) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.count = count;
        this.img = img;
    }
}
