package com.huang.controller.displaycontroller;

import com.huang.mapper.*;
import com.huang.pojo.typesModule.commonimg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 全部图片展示
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */

@RestController
public class showallimgs {

    @Autowired
    com.huang.mapper.animalmapper animalmapper;
    @Autowired
    com.huang.mapper.constructmapper constructmapper;
    @Autowired
    com.huang.mapper.faceMapper.humanmapper humanmapper;
    @Autowired
    com.huang.mapper.vehiclemapper vehiclemapper;
    @Autowired
    allimgmapper allimgmapper;
    @Autowired
    com.huang.mapper.scenerymapper scenerymapper;
    @Autowired
    othermapper othermapper;

    @RequestMapping("/showallimgs")
    public String[] showpic(@RequestParam("user") String master) {
        System.out.println("进来了");
        System.out.println(master);
        List<commonimg> commonimgs = allimgmapper.queryallimgs(master);
        System.out.println(commonimgs.size());
        String[] res=new String[commonimgs.size()];
        for (int i = 0; i < commonimgs.size(); i++) {
            res[i]=commonimgs.get(i).getName();
        }
        return res;
    }


}
