package com.tr.auth.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/**
 * @Author: TR
 */
@Configuration
public class DefaultTokenServicesConfig {

    @Resource
    private TokenStore tokenStore;

    @Resource
    private AuthenticationManager authenticationManager;

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new MyTokenEnhancer();
    }

    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore);
        defaultTokenServices.setTokenEnhancer(tokenEnhancer());
        defaultTokenServices.setAuthenticationManager(authenticationManager);
        // 支持使用refresh token刷新access token
        defaultTokenServices.setSupportRefreshToken(true);
        // 允许重复使用refresh token
        defaultTokenServices.setReuseRefreshToken(true);
        return defaultTokenServices;
    }

}
