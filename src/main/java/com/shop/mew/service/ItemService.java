package com.shop.mew.service;

import com.shop.mew.domain.item.Item;
import com.shop.mew.domain.item.ItemRepository;
import com.shop.mew.web.dto.ItemRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public Item findOne (Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("상품이 존재하지 않습니다."));
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Transactional
    public void addItem(ItemRequestDto.Register itemRequestDto) {
        itemRepository.save(Item.builder()
                .name(itemRequestDto.getName())
                .category(itemRequestDto.getCategory())
                .price(itemRequestDto.getPrice())
                .count(itemRequestDto.getCount())
                .img(itemRequestDto.getImg())
                .build());
    }

    @Transactional
    public Item updateItem (Long id, ItemRequestDto.Update itemRequestDto){
        Item item = findOne(id);
        item.update(item, itemRequestDto);
        return item;
    }
}
