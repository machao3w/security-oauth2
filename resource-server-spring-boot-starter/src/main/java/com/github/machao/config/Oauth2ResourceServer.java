package com.github.machao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * author: mc
 * date: 2020/7/24 14:23
 */
@Configuration
@EnableResourceServer
public class Oauth2ResourceServer extends ResourceServerConfigurerAdapter {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .requestMatchers()
                .antMatchers("/test/**");
    }

//    @Bean
//    @Primary
//    public RemoteTokenServices remoteTokenServices(){
//        final RemoteTokenServices tokenServices = new RemoteTokenServices();
//        tokenServices.setCheckTokenEndpointUrl("http://localhost:8877/oauth/check_token");
//        tokenServices.setClientId("client-a");
//        tokenServices.setClientSecret("client-a-secret");
//        return tokenServices;
//    }

    @Bean
    public RedisTokenStore redisTokenStore(){
        RedisTokenStore redisTokenStore = new RedisTokenStore(redisConnectionFactory);
        redisTokenStore.setPrefix("auth-token:");
        return redisTokenStore;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.resourceId("resource1").stateless(true);
        resources.resourceId("resource1").tokenStore(redisTokenStore()).stateless(true);
    }
}
