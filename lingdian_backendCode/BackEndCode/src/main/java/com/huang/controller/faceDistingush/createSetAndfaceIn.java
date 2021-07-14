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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description  为新面孔创建人脸库（在创建人脸库的同时加入照片）
 * @Author huangyang
 * @date 2021年06月19日 9:37
 */

@RestController
public class createSetAndfaceIn {

    @Autowired
    com.huang.mapper.faceMapper.facesetMapper facesetMapper;
    @Autowired
    com.huang.mapper.faceMapper.humanmapper humanmapper;
    @Autowired
    com.huang.mapper.allimgmapper allimgmapper;


    @RequestMapping("/createSetAndfaceIn")
    public Object createfacesetandfacein(@RequestParam("faceSetName") String faceSetName,
                                         @RequestParam("faceSetDescription") String faceSetDescription,
                                         @RequestParam("user") String master,
                                         @RequestParam("appkey") String appkey,
                                         @RequestParam("files") MultipartFile[] files,
                                         @RequestParam("faceinfo") String faceinfo) throws ClientException, IOException {
        AiFaceBody instance = FaceAi.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(new Date());
        String format1 = sdf1.format(new Date());
//        创建人脸库
        CommonJsonObjectResponse newfaceSet = instance.createFaceSet(faceSetName, faceSetDescription, appkey, null);
        Object faceStoreId = newfaceSet.getCommonResult().get("faceStoreId");
        facesetMapper.addfaceset(faceSetName, format1, faceSetDescription, master, appkey, faceStoreId);
        System.out.println("创建人脸集合===>" + faceStoreId);
        for (MultipartFile file : files) {
//            将照片放进入人脸库
            CommonJsonObjectResponse faceToFile = instance.createFaceToFile(Integer.valueOf(faceStoreId.toString().replace(".0", "")), file.getBytes(), file.getOriginalFilename(),
                    faceinfo, appkey, null);
            System.out.println("照片放入人脸===>" + faceToFile);
            Object faceId = faceToFile.getCommonResult().get("faceId");
//        更新数据库信息
            humanmapper.addhumanInfo(file.getOriginalFilename(), format, file.getSize(), "human", faceinfo,
                    "D:\\1rjb\\webphoto2\\static\\smart_photos\\humans\\" + master + format + file.getOriginalFilename(),
                    master, "123456", master + format + file.getOriginalFilename(), faceStoreId.toString().replace(".0", ""), faceId.toString());
            allimgmapper.updateallimg(file.getOriginalFilename(), format1, file.getSize(), "human", faceinfo,
                    "D:\\1rjb\\webphoto2\\static\\smart_photos\\allimgs\\" + master + format + file.getOriginalFilename(),
                    master, "123456", master + format + file.getOriginalFilename(), null);
            System.out.println("数据库信息更新完毕===>");
            //        文件存储更新
            String filepath = "D:\\1rjb\\webphoto2\\static\\smart_photos\\";
            File dest = new File(filepath + "allimgs\\" + master + format + file.getOriginalFilename());
            File dest1 = new File(filepath + "humans\\" + master + format + file.getOriginalFilename());
            file.transferTo(dest);
            file.transferTo(dest1);
            System.out.println("文件存储完毕===>");
        }
        facesetMapper.updateCount(master,faceStoreId.toString(), files.length);
        return "success";
    }


}
