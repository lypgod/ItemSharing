package com.lypgod.itemsharing.zipkinserver.model.entity;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author lypgod
 */
public class Authority implements GrantedAuthority {
    private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Authority authority1 = (Authority) o;

        return authority.equals(authority1.authority);
    }

    @Override
    public int hashCode() {
        return authority.hashCode();
    }
}
