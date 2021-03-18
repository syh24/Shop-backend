package com.shop.mew.service;

import com.shop.mew.domain.item.Item;
import com.shop.mew.domain.item.ItemRepository;
import com.shop.mew.web.dto.ItemRequestDto;
import com.shop.mew.web.dto.ItemResponseDto;
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
    public ItemResponseDto addItem(ItemRequestDto.Register itemRequestDto) {
        Item saved = itemRepository.save(Item.builder()
                .name(itemRequestDto.getName())
                .category(itemRequestDto.getCategory())
                .price(itemRequestDto.getPrice())
                .count(itemRequestDto.getCount())
                .img(itemRequestDto.getImg())
                .build());
        return new ItemResponseDto(saved.getName(), saved.getCategory(), saved.getPrice(), saved.getCount(),saved.getImg());
    }

    @Transactional
    public ItemResponseDto updateItem (Long id, ItemRequestDto.Update itemRequestDto){
        Item item = findOne(id);
        item.update(item, itemRequestDto);
        return new ItemResponseDto(item.getName(),item.getCategory(),item.getPrice(),item.getCount(),item.getImg());
    }

    @Transactional
    public boolean deleteItem(Long id) {
        try {
            itemRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        return true;
    }
}
