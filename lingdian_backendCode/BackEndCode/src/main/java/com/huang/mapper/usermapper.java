package com.huang.mapper;

import com.huang.pojo.userModule.userInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 用户信息的操作mapper
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */

@Mapper
@Repository
//用户信息
public interface usermapper {
//    增加一个用户信息
    void addUser(String username, String password, int authority);
//    查询一个用户
    userInfo queryUser(String username, String password);
//    注册查重
    userInfo queryUserByname(String username);
//    查询所有用户信息
    List<userInfo> queryusers();
//    修改密码
    void updatepassword(String username,String password);

}
