package com.huang.service;

import com.huang.mapper.usermapper;
import com.huang.pojo.userModule.userInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author huangyang
 * @date 2021年06月30日 9:19
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    usermapper usermapper;


    @Override
    public userInfo queryUserByname(String username) {
        return usermapper.queryUserByname(username);
    }
}
