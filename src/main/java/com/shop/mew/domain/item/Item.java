package com.shop.mew.domain.item;

import com.shop.mew.domain.BaseTimeEntity;
import com.shop.mew.domain.cart.Cart;
import com.shop.mew.domain.review.Review;
import com.shop.mew.web.dto.ItemRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.logging.log4j.util.Strings.isNotEmpty;

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
        checkArgument(isNotEmpty(name), "name must be provided.");
        checkArgument(isNotEmpty(category), "category must be provided.");
        checkArgument(price > 0, "price must greater than 0");
        checkArgument(count > 0, "count must greater than 0");
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.count = count;
        this.img = img;
    }

    public void update (Item items, ItemRequestDto.Update item){
        items.name = item.getName();
        items.category = item.getCategory();
        items.price = item.getPrice();
        items.count = item.getCount();
        items.img = item.getImg();
    }
}
