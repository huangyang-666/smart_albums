package com.huang.controller.faceDistingush;

import com.chinamobile.bcop.api.sdk.ai.core.exception.ClientException;
import com.chinamobile.bcop.api.sdk.ai.core.model.CommonJsonObjectResponse;
import com.chinamobile.bcop.api.sdk.ai.facebody.AiFaceBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 仅用来测试人脸模块的api       用来测试api的，仅后端使用
 * @Author huangyang
 * @date 2021年06月15日 14:13
 */

@RestController
public class testfacemodule {

    @RequestMapping("/testfacemodule")
    public Object test(@RequestParam("facesetid")String facesetid) throws ClientException {
        AiFaceBody instance = FaceAi.getInstance();
        int[] id={116821,116564,116192,116191};
//        人脸库查询
        CommonJsonObjectResponse response = instance.queryFaceSet("", 1, 1000, "123456", null);
        System.out.println(response);
        System.out.println(response.getCommonResult());
//        人脸添加
        return response.getCommonResult();
    }



}
