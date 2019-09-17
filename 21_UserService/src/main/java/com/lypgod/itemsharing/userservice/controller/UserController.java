package com.lypgod.itemsharing.userservice.controller;

import com.lypgod.itemsharing.userservice.model.entity.User;
import com.lypgod.itemsharing.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author lypgod
 */
@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PostMapping("/add")
    public User addUser(@Valid @RequestBody User user) throws Exception {
        return userService.createUser(user);
    }
}
