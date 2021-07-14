//package com.huang.controller.userRegister;
//
//import com.huang.pojo.userModule.userInfo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
///**
// * @Description 用户登录
// * @Author huangyang
// * @date 2021年05月29日 14:13
// */
//
//@RestController
//public class userLogin {
//
//    @Autowired
//    com.huang.mapper.usermapper usermapper;
//
//    @RequestMapping("/userlogin")
//    public String userlogin(@RequestBody userInfo user) {
//        userInfo res = usermapper.queryUser(user.getUsername(), user.getPassword());
//        if (res!=null){
//            System.out.println("success");
//            return "success";
//        }else {
//            System.out.println("false");
//            return "false";         //返回false===》前端显示：用户名或者密码错误
//        }
//    }
//
//}
