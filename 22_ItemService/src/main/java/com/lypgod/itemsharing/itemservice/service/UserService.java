package com.lypgod.itemsharing.itemservice.service;

import com.lypgod.itemsharing.itemservice.model.entity.User;
import com.lypgod.itemsharing.itemservice.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author lypgod
 */
@Service
public class UserService {
    @Resource
    private UserRepository userRepository;

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
