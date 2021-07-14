package com.huang.controller.displaycontroller;

import com.huang.mapper.*;
import com.huang.pojo.faceModule.human;
import com.huang.pojo.typesModule.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 查询当前用户某个类下的所有照片
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */

@RestController
public class diftypeDisplay {
//    展示用户下的某个类的所有照片
    @Autowired
    animalmapper animalmapper;
    @Autowired
    constructmapper constructmapper;
    @Autowired
    com.huang.mapper.faceMapper.humanmapper humanmapper;
    @Autowired
    vehiclemapper vehiclemapper;
    @Autowired
    allimgmapper allimgmapper;
    @Autowired
    scenerymapper scenerymapper;
    @Autowired
    othermapper othermapper;

    @RequestMapping("/diftypedisplay")
    public Object diftypeshow(@RequestParam("user")String master,
                              @RequestParam("type")String type){
//  human(1) animal(2) scenery(3) vehicle(4) construct(5)
        System.out.println(master+"====>"+type);
        if (type.equals("2")) {
            List<animal> animals = animalmapper.queryanimals(master);
            for (int i = 0; i < animals.size(); i++) {
                System.out.println(animals.get(i));
            }
            return animals;
        }
        if (type.equals("1")) {
            List<human> humans = humanmapper.queryhumans(master);
            return humans;
        }
        if (type.equals("3")) {
            List<scenery> sceneries = scenerymapper.querysceneries(master);
            return sceneries;
        }
        if (type.equals("4")) {
            List<vehicle> vehicles = vehiclemapper.queryvehicles(master);
            return vehicles;
        }
        if (type.equals("5")) {
            List<construct> constructs = constructmapper.queryconstructs(master);
            return constructs;
        }
        return null;

    }



}
