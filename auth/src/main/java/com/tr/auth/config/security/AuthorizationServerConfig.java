package com.tr.auth.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @Author TR
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Resource
    private DataSource dataSource;
    @Resource
    private TokenStore tokenStore; // TokenStoreConfig 中定义
    @Resource
    private JwtAccessTokenConverter jwtAccessTokenConverter; // TokenStoreConfig 中定义
    @Resource
    private AuthenticationManager authenticationManager; // WebSecurityConfig 中定义
    @Resource
    private UserDetailsService userDetailsService; // UserServiceImpl 中重写
    @Resource
    private TokenEnhancer tokenEnhancer; // WebSecurityConfig 中定义

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 设置 clients，使用 JdbcClientDetailsService 自动获取 oauth_client_details 表信息
        // 如需自定义，在这里配置自定义 Client 内容，如使用 ClientService 获取 clientList 后通过 builder（用 clients.inMemory() 获取）配置，参考 orion
        clients.withClientDetails(new JdbcClientDetailsService(dataSource));
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        if (jwtAccessTokenConverter != null) {
            if (tokenEnhancer != null) {
                // token 增强（这里的主要目的是使用 MyTokenEnhancer 给 token 增加自定义信息）
                TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
                tokenEnhancerChain.setTokenEnhancers(
                        Arrays.asList(tokenEnhancer, jwtAccessTokenConverter));
                endpoints.tokenEnhancer(tokenEnhancerChain);
            } else {
                endpoints.accessTokenConverter(jwtAccessTokenConverter);
            }
        }
        endpoints
                .authenticationManager(authenticationManager)  // 要支持密码登录授权模式，需配置此项
                .userDetailsService(userDetailsService)        // 要支持密码登录授权模式，需配置此项
                .tokenStore(tokenStore)
                .accessTokenConverter(jwtAccessTokenConverter);
//                .setClientDetailsService(ClientService)      // 如果上面 ClientDetailsServiceConfigurer 采用自定义配置，这里需要开启配置，并且 ClientService 需要继承 ClientDetailsService 重写 loadClientByClientId(String clientId) 方法，参考 orion
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()")   // isAuthenticated()
                .tokenKeyAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

}
