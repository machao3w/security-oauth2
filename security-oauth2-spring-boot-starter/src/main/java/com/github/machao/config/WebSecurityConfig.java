package com.github.machao.config;

import com.github.machao.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * author: mc
 * date: 2020/7/24 10:36
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailService myUserDetailService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(myUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
        auth.inMemoryAuthentication()
                .withUser("machao")
                .password(passwordEncoder().encode("123456"))
                .authorities(AuthorityUtils.NO_AUTHORITIES)
                .and()
                .withUser("machao3w")
                .password(passwordEncoder().encode("123456"))
                .authorities(AuthorityUtils.NO_AUTHORITIES);
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/login/**").permitAll()
//                .antMatchers(HttpMethod.OPTIONS).permitAll()
                .anyRequest().authenticated() //所有请求都需要通过认证
                .and()
                .httpBasic() //Basic登录
                .and()
                .csrf().disable(); //关跨域保护
    }
}
