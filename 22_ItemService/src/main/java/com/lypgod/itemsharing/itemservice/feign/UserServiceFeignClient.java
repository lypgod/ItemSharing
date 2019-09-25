package com.lypgod.itemsharing.itemservice.feign;

import com.lypgod.itemsharing.itemservice.model.entity.User;
import com.lypgod.itemsharing.itemservice.util.usercontext.UserContext;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author lypgod
 */
@FeignClient(value = "user-service", fallback = UserServiceFeignClientFallback.class)
public interface UserServiceFeignClient {
    /**
     * get User By Username
     * @param username String
     * @param token String
     * @return User
     */
    @GetMapping("/v1/user/{username}")
    User getUserByUsername(@PathVariable String username, @RequestHeader(UserContext.AUTH_TOKEN) String token);
}
