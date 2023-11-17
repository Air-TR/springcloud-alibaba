package com.tr.auth.common.kit;

import cn.hutool.jwt.JWTUtil;

/**
 * @Author: TR
 */
public class JwtKit {

    public static String getUsername(String token) {
        return JWTUtil.parseToken(token).getPayload().getClaimsJson().getStr("user_name");
    }

    public static String getUsername() {
        return getUsername(TokenKit.getToken(HttpKit.getRequest()));
    }

}
