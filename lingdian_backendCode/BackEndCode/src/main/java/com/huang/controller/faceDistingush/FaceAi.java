package com.huang.controller.faceDistingush;

import com.chinamobile.bcop.api.sdk.ai.core.constant.Region;
import com.chinamobile.bcop.api.sdk.ai.facebody.AiFaceBody;

/**
 * @Description 获取人脸模块api能力
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */


//获取人脸识别模块能力的封装工具类
public class FaceAi {
    private static String ak="CIDC-AK-ed527649-69c4-4297-93ff-bc8fb34201ee";
    private static String sk="CIDC-SK-c4b503bb-5cce-45a8-9019-cd3c7694e960";
    private static AiFaceBody aiFaceBody;

    public static AiFaceBody getInstance(){
       if (aiFaceBody==null){
           aiFaceBody = new AiFaceBody(Region.POOL_CS, ak, sk);
       }
       return aiFaceBody;
    }

}
