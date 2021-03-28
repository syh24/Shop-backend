package com.shop.mew.domain.review;


import com.shop.mew.domain.item.Item;
import com.shop.mew.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

@Getter
@NoArgsConstructor(access = PROTECTED)
@Entity
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private int rate;

    @ManyToOne(fetch = LAZY)
    private Item item;

    @ManyToOne(fetch = LAZY)
    private User user;

    public Review(String content, int rate) {
        this.content = content;
        this.rate = rate;
    }
}
