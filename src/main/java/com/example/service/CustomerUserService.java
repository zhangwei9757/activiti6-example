//package com.example.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author jwt
// * @date 2019-8-20
// */
//@Service
//@Slf4j
//public class CustomerUserService implements UserDetailsService {
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        log.info("登录用户名： ", s);
//        String password = passwordEncoder.encode("123456");
//        log.info("数据库中查询对应用户名密码： ", password);
//        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
//        return new User(s,
//                password,
//                true,
//                true,
//                true,
//                true,
//                authorities);
//    }
//}
