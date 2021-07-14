package com.huang.controller.faceDistingush;

import com.huang.mapper.faceMapper.facesetMapper;
import com.huang.mapper.faceMapper.humanmapper;
import com.huang.pojo.faceModule.faceset;
import com.huang.pojo.faceModule.facesetRes;
import com.huang.pojo.faceModule.human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Description 展示全部人脸库照片的接口
 * @Author huangyang
 * @date 2021年06月16日 20:05
 */
@RestController
public class faceSetDisplay {

    @Autowired
    facesetMapper facesetMapper;
    @Autowired
    com.huang.mapper.faceMapper.humanmapper humanmapper;

    //    查询当前有多少人脸库,返回人脸库数量和人脸库的id
    @RequestMapping("/queryfaceSets")
    public List<facesetRes> queryfacesetnums(@RequestParam("username") String master) {
        List<faceset> facesets = facesetMapper.queryFaceSets(master);
        List<facesetRes> res = new ArrayList<>();
        for (faceset faceset : facesets) {
            res.add(new facesetRes(Integer.parseInt(faceset.getFaceStoreId().replace(".0", "")), faceset.getName(),
                    faceset.getDetails(), faceset.getUploadTime()));
        }
        return res;
    }

    //    查询人脸库中的所有照片信息
    @RequestMapping("/queryfacesOfset")
    public List<human> queryfacesOfSet(@RequestParam("username") String master,
                                       @RequestParam("faceStoreId") int id) {
        List<human> faces = humanmapper.queryfaces(master, id + "");
        return faces;
    }


}
