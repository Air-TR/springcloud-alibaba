package com.tr.goods.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.util.StringUtils;

@Configuration
@EnableResourceServer
public class ResourceConfig extends ResourceServerConfigurerAdapter {

    @Value("${security.ex-path}")
    private String excludedPath;

    @Value("${security.enable}")
    private Boolean securityEnable;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        if (!securityEnable) excludedPath = "/**";
        if (StringUtils.isEmpty(excludedPath)) {
            http.authorizeRequests().anyRequest().authenticated().and().cors();
        } else {
            http
                    .authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll()
                    .antMatchers(excludedPath.split(","))
                    .permitAll()
                    .and().authorizeRequests()
                    .anyRequest().authenticated().and().cors();
        }
    }
}
