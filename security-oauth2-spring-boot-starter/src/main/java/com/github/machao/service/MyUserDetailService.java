package com.github.machao.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * author: mc
 * date: 2020/7/24 9:46
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("guest");
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(simpleGrantedAuthority);

        return User.withUsername(s)
                .password(new BCryptPasswordEncoder().encode("123456"))
                .authorities(simpleGrantedAuthority)
                .build();
    }
}
