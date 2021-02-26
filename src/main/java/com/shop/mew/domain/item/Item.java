package com.shop.mew.domain.item;

import com.shop.mew.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Item extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    private String category;

    private Integer price;

    @ColumnDefault("0")
    private Integer count;  //구매 개수

    private Integer totalCount; //총 개수

    private String img;

    private Integer rate; //평점

}
