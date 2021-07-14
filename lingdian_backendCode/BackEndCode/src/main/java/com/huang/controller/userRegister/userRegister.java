package com.huang.controller.userRegister;

import com.huang.pojo.userModule.userInfo;
import com.huang.pojo.userModule.userregister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Description 用户注册
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */


@RestController
@CrossOrigin
public class userRegister {

    @Autowired
    com.huang.mapper.usermapper usermapper;

    @RequestMapping("/userRegister")
    public String register(@RequestBody userregister user) {
        String username = user.getUsername();
        String password = user.getPassword();
        String pwdtwo = user.getPwdtwo();
        System.out.println(username + "==>" + password + "===>" + pwdtwo);
        userInfo flag = usermapper.queryUserByname(username); //去重
//        System.out.println(flag.toString());
        if (!pwdtwo.equals(password)) {
            System.out.println("pwddifferent");
            return "pwddifferent";
        }
        if (flag == null && pwdtwo.equals(password)) {
            usermapper.addUser(username, password, 0);
            System.out.println("success");
            return "success";
        } else {
            System.out.println("userexist");
            return "userexist";
        }
    }


}
