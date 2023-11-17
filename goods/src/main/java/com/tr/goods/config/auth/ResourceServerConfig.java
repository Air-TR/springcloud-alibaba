package com.tr.goods.config.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.annotation.Resource;

/**
 * @Author TR
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Value("${spring.application.name}")
    private String resourceId;

    @Value("${security.enable}")
    private Boolean securityEnable;

    @Value("${security.ex-path}")
    private String securityExPath;

    @Resource
    private TokenStore tokenStore;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceId)
                .tokenStore(tokenStore)
                .stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        if (!securityEnable) securityExPath = "/**";
        http.authorizeRequests()
                .antMatchers(securityExPath.split(",")).permitAll()
//                .antMatchers("/**").access("#oauth2.hasScope('all')")
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
    }

}