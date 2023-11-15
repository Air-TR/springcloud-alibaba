package com.tr.auth.config;

import com.tr.auth.config.filter.TokenFilter;
import com.tr.auth.config.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @Author: TR
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.enable}")
    private Boolean securityEnable;

    @Value("${security.ex-path}")
    private String securityExPath;

    @Resource
    private UserDetailsServiceImpl userDetailsService;

    @Resource
    private TokenFilter tokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证用户的来源（从数据库获取）
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 将不需要鉴权的请求路径写在 antMatchers() 中，这边是真正让 SpringSecurity 过滤放行的地方，而不在 TokenFilter 判断（TokenFilter 仅做 Token 校验）
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (!securityEnable) securityExPath = "/**";
        http.authorizeRequests()
            .antMatchers(securityExPath.split(","))
            .permitAll().and()
            .authorizeRequests().anyRequest().authenticated()
            .and().csrf().disable().cors();
        // 把 token 校验过滤器添加到过滤器链中
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
