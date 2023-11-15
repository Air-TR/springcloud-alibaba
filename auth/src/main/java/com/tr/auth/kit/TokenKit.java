package com.tr.auth.kit;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: TR
 * @Date: 2023/5/26
 */
public class TokenKit {

    public static String getToken(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }

}
