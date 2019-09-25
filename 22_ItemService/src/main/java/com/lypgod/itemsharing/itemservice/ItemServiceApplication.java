package com.lypgod.itemsharing.itemservice;

import com.lypgod.itemsharing.itemservice.model.entity.Item;
import com.lypgod.itemsharing.itemservice.model.entity.User;
import com.lypgod.itemsharing.itemservice.service.ItemService;
import com.lypgod.itemsharing.itemservice.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

import javax.annotation.Resource;

/**
 * @author lypgod
 */
@Log4j2
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
public class ItemServiceApplication implements CommandLineRunner {
    @Resource
    private UserService userService;
    @Resource
    private ItemService itemService;

    public static void main(String[] args) {
        SpringApplication.run(ItemServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        User userJadams = userService.getUserByUsername("jadams");
        if (userJadams != null) {
            Item item1 = new Item()
                    .setName("Item1")
                    .setStatus("Active")
                    .setItemCondition("New")
                    .setDescription("This is item1 description.")
                    .setUser(userJadams);
            try {
                itemService.addItem(item1);
            } catch (Exception e) {
                log.warn(e.getMessage());
            }

            Item item2 = new Item()
                    .setName("Item2")
                    .setStatus("Inactive")
                    .setItemCondition("Used")
                    .setDescription("This is item2 description.")
                    .setUser(userJadams);
            try {
                itemService.addItem(item2);
            } catch (Exception e) {
                log.warn(e.getMessage());
            }
        }
    }
}
