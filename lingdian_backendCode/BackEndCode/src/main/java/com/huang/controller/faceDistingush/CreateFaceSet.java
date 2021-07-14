package com.huang.controller.faceDistingush;

import com.chinamobile.bcop.api.sdk.ai.core.exception.ClientException;
import com.chinamobile.bcop.api.sdk.ai.core.model.CommonJsonObjectResponse;
import com.chinamobile.bcop.api.sdk.ai.facebody.AiFaceBody;
import com.huang.mapper.faceMapper.facesetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 创建人脸库
 * @Author huangyang
 * @date 2021年06月13日 15:17
 */

@RestController
public class CreateFaceSet {

    @Autowired
    facesetMapper facesetMapper;

//    创建人脸库
    @RequestMapping("/addfaceset")
    public Object faceset(@RequestParam("faceSetName") String name,
                          @RequestParam("faceSetDescription") String details,
                          @RequestParam("username") String master,
                          @RequestParam("appkey") String appkey) {
        AiFaceBody aiFaceBody = FaceAi.getInstance();
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Object faceStoreId=null;
        try {
            CommonJsonObjectResponse response = aiFaceBody.createFaceSet(name,details,appkey,null);
            faceStoreId = response.getCommonResult().get("faceStoreId");
            facesetMapper.addfaceset(name,sdf.format(date),details,master,appkey, faceStoreId);
            System.out.println(response.getCommonResult());
            return response.getCommonResult();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return faceStoreId;
    }




}
