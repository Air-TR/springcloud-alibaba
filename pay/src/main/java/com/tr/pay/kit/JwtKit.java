package com.tr.pay.kit;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWTUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @Author: TR
 */
public class JwtKit {

    /**
     * 返回 token，不带授权类型（如 bearer）
     */
    public static String getToken() {
        return getAuthorization().split(" ")[1];
    }

    /**
     * 返回 authorization，带授权类型（如 bearer）
     */
    public static String getAuthorization() {
        return getAuthorization(getRequest());
    }
    public static String getAuthorization(HttpServletRequest request) {
        return request.getHeader(HttpHeaders.AUTHORIZATION);
    }

    public static JSONObject getClaimsJson() {
        return getClaimsJson(getToken());
    }
    public static JSONObject getClaimsJson(String token) {
        return JWTUtil.parseToken(token).getPayload().getClaimsJson();
    }

    public static String getUsername() {
        return getUsername(getToken());
    }
    public static String getUsername(String token) {
        return getClaimsJson(token).getStr("user_name");
    }

    public static Integer getUserId() {
        return getClaimsJson().getInt("userId");
    }

    public static JSONArray getAuthorities() {
        return getClaimsJson().getJSONArray("authorities");
    }

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
