package com.lypgod.itemsharing.zipkinserver.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @author lypgod
 */
@Configuration
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private static final String[] PUBLIC_MATCHERS = {
            "/actuator/**"
    };

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/")
                .authenticated()
            .antMatchers(PUBLIC_MATCHERS)
                .permitAll();
    }
}
