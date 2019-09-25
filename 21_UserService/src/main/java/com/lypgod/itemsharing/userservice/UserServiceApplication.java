package com.lypgod.itemsharing.userservice;

import com.lypgod.itemsharing.userservice.model.entity.User;
import com.lypgod.itemsharing.userservice.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import javax.annotation.Resource;

/**
 * @author lypgod
 */
@Log4j2
@SpringBootApplication
@EnableEurekaClient
@EnableResourceServer
public class UserServiceApplication implements CommandLineRunner {
    @Resource
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
        User userAdams = new User()
                            .setFirstName("John")
                            .setLastName("Adams")
                            .setUsername("jadams")
                            .setPassword("password")
                            .setEmail("jadams@gmail.com");

        try {
            userService.createUser(userAdams);
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
    }
}
