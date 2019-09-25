package com.lypgod.itemsharing.itemservice.feign;

import com.lypgod.itemsharing.itemservice.model.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author lypgod
 */
@Component
public class UserServiceFeignClientFallback implements UserServiceFeignClient {
    @Override
    public User getUserByUsername(String username, String token) {
        return new User().setId(0L).setUsername(username).setFirstName("Fallback User");
    }
}
