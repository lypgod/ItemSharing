package com.lypgod.itemsharing.itemservice.util.usercontext;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * @author lypgod
 */
@Getter @Setter
@Component
public class UserContext {
    public static final String CORRELATION_ID = "is-correlation-id";
    public static final String AUTH_TOKEN = "Authorization";
    public static final String USER_ID = "is-user-id";

    private String correlationId = "";
    private String authToken = "";
    private String userId = "";
}
