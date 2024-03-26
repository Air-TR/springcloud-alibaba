package com.tr.auth.config.security.filter;

import com.tr.auth.common.constant.RedisKey;
import com.tr.auth.common.kit.JwtKit;
import com.tr.auth.common.kit.ServletKit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: TR
 */
@Component
public class TokenFilter extends OncePerRequestFilter {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 这边过滤每一个进入系统的请求
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取 token
        String token = JwtKit.getAuthorization(request);
        if (StringUtils.isBlank(token)) {
            // 放行
            filterChain.doFilter(request, response);
            return;
        }
        if (!token.startsWith("bearer ") && !token.startsWith("Bearer ")) {
            ServletKit.renderString(response, 403, "token 类型错误，需要以 bearer 开头");
            return;
        }
        token = token.split(" ")[1];
        // 解析 token 获取用户名
        String username = JwtKit.getUsername(token);
        // 从 redis 中获取用户信息
        String userToken = stringRedisTemplate.opsForValue().get(RedisKey.TOKEN + username);
        if (!token.equals(userToken)) {
            ServletKit.renderString(response, 401, "无效 token");
            return;
        }
        // 从 redis 中获取用户权限
//        String authoritiesValue = stringRedisTemplate.opsForValue().get(RedisKey.AUTHORITIES + username);
//        List<SimpleGrantedAuthority> authorities = JSON.parseArray(authoritiesValue, SimpleGrantedAuthority.class);
        // 构造 UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(token, null, null);
        // 存入 SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 放行
        filterChain.doFilter(request, response);
    }

}
