package com.huang.controller;

import com.huang.mapper.allimgmapper;
import com.huang.pojo.typesModule.commonimg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description
 * @Author huangyang
 * @date 2021年06月23日 21:33
 */

@RestController
public class testmapper {
    @Autowired
    com.huang.mapper.allimgmapper allimgmapper;

    @RequestMapping("/helloss")
    public void llll(){
//        验证猜想，选出来的数据是顺序排列的
        List<commonimg> imgs = allimgmapper.queryallimgs("admin");
        System.out.println(imgs.get(imgs.size()-2).getName());

    }



}
