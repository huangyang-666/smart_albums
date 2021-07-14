package com.huang.controller.commonDistingush;

import com.chinamobile.cmss.sdk.ECloudDefaultClient;
import com.chinamobile.cmss.sdk.IECloudClient;
import com.chinamobile.cmss.sdk.http.constant.Region;
import com.chinamobile.cmss.sdk.http.signature.Credential;
import com.chinamobile.cmss.sdk.request.EngineImageClassifyDetectPostRequest;
import com.chinamobile.cmss.sdk.response.EngineImageClassifyDetectResponse;
import com.chinamobile.cmss.sdk.response.bean.EngineClassify;
import com.chinamobile.cmss.sdk.util.JacksonUtil;

import java.io.IOException;
import java.util.List;

/**
 * @Description 通用图像识别工具类，解析出识别结果
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */

//通过本地上传图片文件进行识别----->适用于本地图片上传的同时识别，标记
public class pictureShibie {

    public static String tongyongshibie(String base64){
        Credential credential = new Credential("33e8d33e59bf43babb3908514305f116", "83fa8553795445d2b09035f108ef10d1");
        IECloudClient ecloudClient = new ECloudDefaultClient(credential, Region.POOL_SZ);
        try {
            EngineImageClassifyDetectPostRequest request = new EngineImageClassifyDetectPostRequest();
            request.setImage(String.valueOf(base64));   //图片的base64码
            request.setUserId("huangyang");
            EngineImageClassifyDetectResponse response = ecloudClient.call(request);
            System.out.println("通用识别结果："+JacksonUtil.toJson(response.getBody()));
            if ("OK".equals(response.getState())){
                List<EngineClassify> body = response.getBody();
                return JacksonUtil.toJson(body).substring(JacksonUtil.toJson(body).indexOf(":") + 2, JacksonUtil.toJson(body).lastIndexOf("\""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


}
