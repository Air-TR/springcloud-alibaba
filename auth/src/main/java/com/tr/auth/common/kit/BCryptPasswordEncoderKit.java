package com.tr.auth.common.kit;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: TR
 */
public class BCryptPasswordEncoderKit {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encode(String password) {
        return encoder.encode(password);
    }

    public static Boolean matches(String plaintext, String ciphertext) {
        return encoder.matches(plaintext, ciphertext);
    }

}
