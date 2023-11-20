package com.tr.auth.config.security;

import com.tr.auth.config.security.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @Author: TR
 */
@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.enable}")
    private Boolean securityEnable;

    @Value("${security.ex-path}")
    private String securityExPath;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private TokenFilter tokenFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 将 Spring Security 的 AuthenticationManager 声明成 Bean
     *  声明它的作用是用它帮我们进行认证操作，调用这个 Bean 的 authenticate 方法会由 Spring Security 自动帮我们做认证
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new MyTokenEnhancer();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("root")
//                .password(passwordEncoder().encode("123456")).roles("ADMIN")
//                .and()
//                .passwordEncoder(passwordEncoder());
//    }

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
        // 把 token 校验过滤器添加到过滤器链中
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

}
