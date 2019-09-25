package com.lypgod.itemsharing.itemservice.util.usercontext;

import org.springframework.util.Assert;

/**
 * @author lypgod
 */
public class UserContextHolder {
    private static final ThreadLocal<UserContext> USER_CONTEXT = new ThreadLocal<>();

    public static UserContext getUserContext() {
        UserContext userContext = USER_CONTEXT.get();

        if (userContext == null) {
            USER_CONTEXT.set(new UserContext());
        }

        return USER_CONTEXT.get();
    }

    public static void setUserContext(UserContext userContext) {
        Assert.notNull(userContext, "Only non-null UserContext instance is permitted.");
        USER_CONTEXT.set(userContext);
    }

    public static void removeUserContext() {
        USER_CONTEXT.remove();
    }
}
