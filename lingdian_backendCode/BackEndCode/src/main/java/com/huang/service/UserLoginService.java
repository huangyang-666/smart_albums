package com.huang.service;

import com.huang.mapper.usermapper;
import com.huang.pojo.userModule.userInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("userLoginService")
public class UserLoginService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserServiceImpl userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("在登陆！");
        String password = userService.queryUserByname(s).getPassword();
        return  new User(s, passwordEncoder.encode(password),
                // 此处的角色需要`ROLE_`前缀。Spring Security配置中的角色不需要`ROLE_` 前缀
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));

    }
}
