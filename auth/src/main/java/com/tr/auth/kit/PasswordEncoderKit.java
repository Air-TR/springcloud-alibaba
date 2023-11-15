package com.tr.auth.kit;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: TR
 */
public class PasswordEncoderKit {

    public static void main(String[] args) {
        System.out.println(encode("123456"));
    }

    public static String encode(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

}
