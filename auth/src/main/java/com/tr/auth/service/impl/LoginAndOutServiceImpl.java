package com.tr.auth.service.impl;

import com.alibaba.fastjson.JSON;
import com.tr.auth.constant.RedisKey;
import com.tr.auth.entity.User;
import com.tr.auth.kit.JwtKit;
import com.tr.auth.service.LoginAndOutService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: TR
 */
@Service
public class LoginAndOutServiceImpl implements LoginAndOutService {

    @Value("${token.alive-time}")
    private long tokenAliveTime;

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // 获取 userDetail
        org.springframework.security.core.userdetails.User userDetail = (org.springframework.security.core.userdetails.User) authenticate.getPrincipal();
        // 使用 username 生成 token（如要用 user_id 生成，根据 username 去获取 user_id）
        String token = JwtKit.createJWT(userDetail.getUsername(), tokenAliveTime * 1000);
        // token 存入 redis
        stringRedisTemplate.opsForValue().set(RedisKey.TOKEN + userDetail.getUsername(), token, tokenAliveTime, TimeUnit.SECONDS);
        // authorities 存入 redis
        stringRedisTemplate.opsForValue().set(RedisKey.AUTHORITIES + userDetail.getUsername(), JSON.toJSONString(userDetail.getAuthorities()), tokenAliveTime, TimeUnit.SECONDS);
        return token;
    }

    @Override
    public Boolean logout(String token) {
        String username = JwtKit.getUsername(token);
        return stringRedisTemplate.delete(RedisKey.TOKEN + username) && stringRedisTemplate.delete(RedisKey.AUTHORITIES + username);
    }

}
