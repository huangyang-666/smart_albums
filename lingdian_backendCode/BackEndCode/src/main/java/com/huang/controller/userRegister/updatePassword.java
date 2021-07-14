package com.huang.controller.userRegister;

import com.huang.mapper.usermapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Author huangyang
 * @date 2021年07月12日 15:03
 */

@RestController
public class updatePassword {
    @Autowired
    com.huang.mapper.usermapper usermapper;

    @RequestMapping("/updatepassword")
    public String updatepassword(@RequestParam("user")String username,
                                 @RequestParam("password")String passwod,
                                 @RequestParam("twopwd")String twopwd){
        System.out.println("hello");
        if(!passwod.equals(twopwd)){    //两次密码不一致
            return "faile";
        }
        usermapper.updatepassword(username,passwod);
        return "ok";
    }



}
