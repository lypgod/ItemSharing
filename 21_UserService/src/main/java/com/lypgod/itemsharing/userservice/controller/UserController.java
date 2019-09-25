package com.lypgod.itemsharing.userservice.controller;

import com.lypgod.itemsharing.userservice.model.entity.User;
import com.lypgod.itemsharing.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author lypgod
 */
@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Value("${server.port}")
    String serverPort;

    @Resource
    private UserService userService;

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        user.setEmail(user.getUsername() + "@" + serverPort);
        return user;
    }

    @PostMapping("/add")
    public User addUser(@Valid @RequestBody User user) throws Exception {
        return userService.createUser(user);
    }
}
