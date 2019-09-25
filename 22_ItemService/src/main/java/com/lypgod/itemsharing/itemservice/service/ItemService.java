package com.lypgod.itemsharing.itemservice.service;

import com.lypgod.itemsharing.itemservice.feign.UserServiceFeignClient;
import com.lypgod.itemsharing.itemservice.model.entity.Item;
import com.lypgod.itemsharing.itemservice.model.entity.User;
import com.lypgod.itemsharing.itemservice.model.repository.ItemRepository;
import com.lypgod.itemsharing.itemservice.util.usercontext.UserContextHolder;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author lypgod
 */
@Service
@Log4j2
public class ItemService {
    @Resource
    private ItemRepository itemRepository;
    @Resource
    private UserServiceFeignClient userServiceFeignClient;

    public Item addItem(Item item) throws Exception {
        if (itemRepository.existsByName(item.getName())) {
            throw new Exception("Item already exists!");
        }

        return itemRepository.save(item);
    }

    @Transactional(rollbackOn = Exception.class)
    public Item addItemByUsername(Item item, String username) throws Exception {
        if (itemRepository.existsByName(item.getName())) {
            throw new Exception("Item already exists!");
        }

        User user = Optional.of(getUserByUsername(username)).orElseThrow(() -> new Exception("username doesn't exist."));
        item.setUser(user);
        return itemRepository.save(item);
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public List<Item> getItemsByUsername(String username) throws Exception {
        User user = Optional.of(getUserByUsername(username)).orElseThrow(() -> new Exception("username doesn't exist."));
        return itemRepository.findAllByUser(user);
    }

    public Item getItemById(Long id) throws Exception {
        return itemRepository.findById(id).orElseThrow(() -> new Exception("Item not found!"));
    }

    public Item updateItem(Long id, Item item) {
        item.setId(id);
        return itemRepository.save(item);
    }

    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }

    public User getUserByUsername(String username) {
        log.warn("ItemService - ItemService#getUserByUsername got Correlation-Id: {}", UserContextHolder.getUserContext().getCorrelationId());
        return userServiceFeignClient.getUserByUsername(username, UserContextHolder.getUserContext().getAuthToken());
    }
}
