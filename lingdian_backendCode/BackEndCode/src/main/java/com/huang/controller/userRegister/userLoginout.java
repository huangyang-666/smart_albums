package com.huang.controller.userRegister;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author huangyang
 * @date 2021年06月29日 19:49
 */

//退出登录
@RestController
@CrossOrigin
public class userLoginout {

    @RequestMapping("/logInInfo")
    public String loginInfo(){
        return "请先登录！";
    }

    @RequestMapping("/logoutSuccess")
    public String logoutSuccess(){
        return "退出成功！";
    }


}
