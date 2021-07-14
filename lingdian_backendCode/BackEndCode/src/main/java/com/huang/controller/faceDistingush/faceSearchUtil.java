package com.huang.controller.faceDistingush;

import com.chinamobile.bcop.api.sdk.ai.core.exception.ClientException;
import com.chinamobile.bcop.api.sdk.ai.core.model.CommonJsonObjectResponse;
import com.chinamobile.bcop.api.sdk.ai.facebody.AiFaceBody;
import com.huang.pojo.faceModule.faceset;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * @Description  人脸搜索工具类
 * @Author huangyang
 * @date 2021年06月17日 21:17
 */

public class faceSearchUtil {

    public HashMap<Integer, String> facesearch(String master,String format1,MultipartFile[] files, List<faceset> facesets) throws ClientException, IOException {
//        将照片放入人脸库中进行匹配，匹配好的进行放到此人脸库中
        AiFaceBody instance = FaceAi.getInstance();
        HashMap<Integer, String> res = new HashMap<>();             //正常序号开始的是能匹配的，-1开始的是新面孔
        for (int i = 0; i < files.length; i++) {
            for (faceset faceset : facesets) {
//                将照片放到人脸库中进行匹配
                System.out.println(files[i].getOriginalFilename());
                CommonJsonObjectResponse response = instance.faceSearch("D:\\1rjb\\webphoto2\\static\\smart_photos\\allimgs\\" +master+format1+ files[i].getOriginalFilename(),
                        faceset.getFaceStoreId().replace(".0",""), 1, faceset.getAppkey(), null);
                System.out.println(response);
                String results = response.getCommonResult().get("results").toString();
                double confidence = Double.parseDouble(results.substring(results.lastIndexOf("=") + 1, results.length() - 2));
                System.out.println(confidence);
                if (response.getCommonResult() != null && confidence >= 0.7) {    //置信度划分设置为0.7(置信度匹配史低为0.7)
                    System.out.println("匹配上了===>" + faceset.getFaceStoreId() + "==>" + response.getCommonResult());
//                将能匹配的照片上传到人脸库中
                    CommonJsonObjectResponse faceToFile = instance.createFaceToFile(Integer.valueOf(faceset.getFaceStoreId().replace(".0","")), files[i].getBytes(), files[i].getOriginalFilename(),
                            "",faceset.getAppkey(), null);
                    Object faceId = faceToFile.getCommonResult().get("faceId");
                    res.put(i, faceset.getFaceStoreId().replace(".0","") + "/" + faceId);
                    break;          //有匹配就跳出
                } else if (faceset == facesets.get(facesets.size() - 1)) {
                    res.put(i,"-1");      //找出没有匹配的人脸
                }

//
            }
        }
        return res;

    }}
