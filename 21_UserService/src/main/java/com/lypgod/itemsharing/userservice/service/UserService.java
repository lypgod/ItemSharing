package com.lypgod.itemsharing.userservice.service;

import com.lypgod.itemsharing.userservice.model.entity.Role;
import com.lypgod.itemsharing.userservice.model.entity.User;
import com.lypgod.itemsharing.userservice.model.repository.RoleRepository;
import com.lypgod.itemsharing.userservice.model.repository.UserRepository;
import com.lypgod.itemsharing.userservice.util.SecurityUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author lypgod
 */
@Service
public class UserService {
    @Resource
    private UserRepository userRepository;
    @Resource
    private RoleRepository roleRepository;

    public User getUserByUsername(String username) {
//        simulateLoading();
        return userRepository.findByUsername(username);
    }

    private void simulateLoading() {
        if (new Random().nextInt(3) == 2) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public User createUser(User user) throws Exception {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new Exception("Username exists!");
        }

        user.setPassword(SecurityUtil.passwordEncoder().encode(user.getPassword()));
        Role role = roleRepository.findById(1L).orElse(new Role().setId(1L).setName("ROLE_USER"));
        user.addRole(role);
        return userRepository.save(user);
    }
}
