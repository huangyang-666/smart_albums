package com.huang.controller.faceDistingush;

import com.chinamobile.bcop.api.sdk.ai.core.exception.ClientException;
import com.chinamobile.bcop.api.sdk.ai.core.model.CommonJsonObjectResponse;
import com.chinamobile.bcop.api.sdk.ai.facebody.AiFaceBody;
import com.huang.mapper.allimgmapper;
import com.huang.mapper.faceMapper.facesetMapper;
import com.huang.mapper.faceMapper.humanmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description     删除用户指定的人脸库
 * @Author huangyang
 * @date 2021年06月16日 12:19
 */
@RestController
public class deleteFaceSet {

    @Autowired
    facesetMapper facesetmapper;
    @Autowired
    com.huang.mapper.allimgmapper allimgmapper;
    @Autowired
    humanmapper humanmapper;

    @RequestMapping("/deleteFaceSet")
    public Object delfaceSet(@RequestParam("usernane")String master,
                             @RequestParam("faceSetId")String faceSetId) throws ClientException {
        //删除人脸库-->人脸库api,人脸照片的数据库信息(allimgs,humans)
        AiFaceBody instance = FaceAi.getInstance();
        String appkey = facesetmapper.getAppKey(faceSetId).getAppkey();
//        删除人脸库api
        CommonJsonObjectResponse response = instance.deleteFaceSet(faceSetId, appkey, null);
        System.out.println("人脸库删除操作:"+response);
//        删除人脸库数据
        facesetmapper.delFaceSet(master, faceSetId);
//        删除allimgs数据库数据
        allimgmapper.delfaces(master, faceSetId);
//        删除humans数据库数据
        humanmapper.delfaces(master, faceSetId);




        return "success";
    }


}
