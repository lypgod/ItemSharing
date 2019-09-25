package com.lypgod.itemsharing.itemservice.controller;

import com.lypgod.itemsharing.itemservice.model.entity.Item;
import com.lypgod.itemsharing.itemservice.model.entity.User;
import com.lypgod.itemsharing.itemservice.service.ItemService;
import com.lypgod.itemsharing.itemservice.util.usercontext.UserContextHolder;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author lypgod
 */
@RestController
@RequestMapping("/v1/item")
@Log4j2
public class ItemController {
    @Resource
    private ItemService itemService;

    @GetMapping("/all")
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/byUser")
    public List<Item> getAllItemsByUsername(@RequestParam String username) throws Exception {
        return itemService.getItemsByUsername(username);
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id) throws Exception {
        return itemService.getItemById(id);
    }

    @PostMapping("/add")
    public Item addItem(@Valid @RequestBody Item item, @RequestParam String username) throws Exception {
        return itemService.addItemByUsername(item, username);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @Valid @RequestBody Item item) {
        return itemService.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public void deleteItemById(@PathVariable Long id) {
        itemService.deleteItemById(id);
    }

    @GetMapping("/user/{username}")
    public User getUserByUsername(@PathVariable String username) {
        log.warn("ItemService - ItemController got Correlation-Id: {}", UserContextHolder.getUserContext().getCorrelationId());
        return itemService.getUserByUsername(username);
    }
}
