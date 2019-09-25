package com.lypgod.itemsharing.zipkinserver.util;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author lypgod
 */
@Component
public class SecurityUtil {
    public static final String SALT = "salt";

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
    }

    @Bean
    public static String randomPassword() {
        String saltChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder passwordBuilder = new StringBuilder();
        Random random = new Random();
        int passwordLength = 18;
        while (passwordBuilder.length() < passwordLength) {
            passwordBuilder.append(saltChars.charAt((int)(random.nextFloat() * saltChars.length())));
        }

        return passwordBuilder.toString();
    }
}
