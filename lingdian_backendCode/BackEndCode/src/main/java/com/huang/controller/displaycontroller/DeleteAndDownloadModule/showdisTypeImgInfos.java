package com.huang.controller.displaycontroller.DeleteAndDownloadModule;

import com.huang.mapper.*;
import com.huang.pojo.faceModule.human;
import com.huang.pojo.typesModule.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description  展示某个类别的照片的全部信息
 * @Author huangyang
 * @date 2021年06月21日 1:21
 */
@RestController
public class showdisTypeImgInfos {
    @Autowired
    com.huang.mapper.animalmapper animalmapper;
    @Autowired
    com.huang.mapper.constructmapper constructmapper;
    @Autowired
    com.huang.mapper.faceMapper.humanmapper humanmapper;
    @Autowired
    com.huang.mapper.vehiclemapper vehiclemapper;
    @Autowired
    com.huang.mapper.scenerymapper scenerymapper;
    @Autowired
    othermapper othermapper;

    @RequestMapping("/showdistypeinfos")
    public Object showdistypeimgs(@RequestParam("user")String master,
                                  @RequestParam("type")String type){
        if (type.equals("animal")){
            List<animal> list = animalmapper.queryanimals(master);
            return list;
        }
        if (type.equals("construct")){
            List<construct> list = constructmapper.queryconstructs(master);
            return list;
        }
        if (type.equals("human")){
            List<human> list = humanmapper.queryhumans(master);
            return list;
        }
        if (type.equals("vehicle")){
            List<vehicle> list = vehiclemapper.queryvehicles(master);
            return list;
        }
        if (type.equals("scenery")){
            List<scenery> list = scenerymapper.querysceneries(master);
            return list;
        }
        if (type.equals("other")){
            List<other> list = othermapper.queryothers(master);
            return list;
        }
        return null;
    }



}
