package com.lypgod.itemsharing.itemservice.model.repository;

import com.lypgod.itemsharing.itemservice.model.entity.Item;
import com.lypgod.itemsharing.itemservice.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author lypgod
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    /**
     * exists By itemName
     *
     * @param itemName String
     * @return boolean
     */
    boolean existsByName(String itemName);

    /**
     * findAll By User
     *
     * @param user User
     * @return List<Item>
     */
    List<Item> findAllByUser(User user);
}
