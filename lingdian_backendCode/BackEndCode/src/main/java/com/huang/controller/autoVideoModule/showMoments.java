package com.huang.controller.autoVideoModule;

import com.huang.mapper.moments.Momentsmapper;
import com.huang.pojo.moments.happymoment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 展示用户的精彩时刻
 * @Author huangyang
 * @date 2021年06月26日 9:47
 */

@RestController
public class showMoments {

    @Autowired
    Momentsmapper momentsmapper;

    @RequestMapping("/showmoments")
    public List<happymoment> querymoments(@RequestParam("user")String master,
                                          @RequestParam("type")String type){
        System.out.println(type);
        System.out.println("精彩时刻");
//        在这个地方加入初始视频
        String filename="";
        if (type.equals("animal")) filename="1099.mp4";
        if (type.equals("human")) filename="1183.mp4";
        if (type.equals("scenery")) filename="1239.mp4";
        if (type.equals("vehicle")) filename="1287LR.mp4";
        if (type.equals("construct")) filename="1425.mp4";
        if (type.equals("all")) filename="1325_1.mp4";
        List<happymoment> moments = momentsmapper.querymoments(master,type);
        moments.add(new happymoment(999,filename,"all","","",""));
//        这里返回了所有信息，用的时候取name
        return moments;
    }


}
