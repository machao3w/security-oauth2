package com.github.machao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * author: mc
 * date: 2020/7/24 9:20
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String finalSecret = new BCryptPasswordEncoder().encode("1234567");

        clients.inMemory()
                .withClient("client-a")
                .secret(passwordEncoder.encode("client-a-secret"))
                .authorizedGrantTypes("authorization_code")
                .scopes("read_user_info")
                .resourceIds("resource1")
                .redirectUris("http://localhost:8877/callback")
                .autoApprove(true);

    }


    //    @Override
    //    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    //        super.configure(endpoints);
    //    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients()
        .checkTokenAccess("isAuthenticated()");
    }
}
