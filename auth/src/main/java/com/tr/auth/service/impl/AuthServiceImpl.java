package com.tr.auth.service.impl;

import com.google.common.collect.Maps;
import com.tr.auth.common.constant.RedisKey;
import com.tr.auth.common.kit.BCryptPasswordEncoderKit;
import com.tr.auth.common.kit.JwtKit;
import com.tr.auth.config.security.entity.CusAuthentication;
import com.tr.auth.entity.User;
import com.tr.auth.repository.UserRepository;
import com.tr.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: TR
 */
@Service
public class AuthServiceImpl implements AuthService {

    @Value("${security.token.alive-time}")
    private long tokenAliveTime;

    @Resource
    private UserRepository userRepository;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private TokenEndpoint tokenEndpoint;

    @Override
    public User register(User user) {
        user.setPassword(BCryptPasswordEncoderKit.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public OAuth2AccessToken login(User user) {
        CusAuthentication cusAuthentication = new CusAuthentication();
        cusAuthentication.setName("auth"); // 传 oauth_client_details 表的 client_id
        cusAuthentication.setAuthenticated(true);
        Map<String, String> params = Maps.newLinkedHashMap();
        params.put("grant_type", "password");
        params.put("username", user.getUsername());
        params.put("password", user.getPassword());
        try {
            OAuth2AccessToken accessToken = tokenEndpoint.postAccessToken(cusAuthentication, params).getBody();
            // token 存进 redis
            stringRedisTemplate.opsForValue().set(RedisKey.TOKEN + JwtKit.getUsername(accessToken.getValue()), accessToken.getValue(), tokenAliveTime, TimeUnit.SECONDS);
            return accessToken;
        } catch (HttpRequestMethodNotSupportedException e) {
            throw new RuntimeException(e);
        }

//        // 获取 userDetail
//        org.springframework.security.core.userdetails.User userDetail = (org.springframework.security.core.userdetails.User) authenticate.getPrincipal();
//        // 使用 username 生成 token（如要用 user_id 生成，根据 username 去获取 user_id）
//        String token = JwtKit.createJWT(userDetail.getUsername(), tokenAliveTime * 1000);
//        // token 存入 redis
//        stringRedisTemplate.opsForValue().set(RedisKey.TOKEN + userDetail.getUsername(), token, tokenAliveTime, TimeUnit.SECONDS);
//        // authorities 存入 redis
//        stringRedisTemplate.opsForValue().set(RedisKey.AUTHORITIES + userDetail.getUsername(), JSON.toJSONString(userDetail.getAuthorities()), tokenAliveTime, TimeUnit.SECONDS);
//        return token;
    }

    @Override
    public Boolean logout(String token) {
        String username = JwtKit.getUsername(token);
//        return stringRedisTemplate.delete(RedisKey.TOKEN + username) && stringRedisTemplate.delete(RedisKey.AUTHORITIES + username);
        return stringRedisTemplate.delete(RedisKey.TOKEN + username);
    }

}
