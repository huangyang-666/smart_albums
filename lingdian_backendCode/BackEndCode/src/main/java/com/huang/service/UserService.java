package com.huang.service;

import com.huang.pojo.userModule.userInfo;

/**
 * @Description
 * @Author huangyang
 * @date 2021年06月30日 9:18
 */

public interface UserService {
    userInfo queryUserByname(String username);
}
