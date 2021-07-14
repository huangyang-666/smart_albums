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

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 用户可以选择在指定的人脸库中加入人脸照片
 * @Author huangyang
 * @date 2021年06月13日 20:46
 */

@RestController
public class addFace {

    @Autowired
    facesetMapper facesetMapper;
    @Autowired
    humanmapper humanmapper;
    @Autowired
    com.huang.mapper.allimgmapper allimgmapper;


    @RequestMapping("/addface")
    public Object addface(@RequestParam("faceSetId")Integer faceSetId,
                          @RequestParam("files")MultipartFile[] files,
                          @RequestParam("faceName")String faceName,
                          @RequestParam("faceInfo")String faceInfo,
                          @RequestParam("username")String master) throws IOException, ClientException {
        AiFaceBody aiFaceBody = FaceAi.getInstance();
        System.out.println(faceSetId+" "+ files.length+" "+faceName+" "+faceInfo+" "+master);
//        根据faceSetId查询appkey
        String appKey = facesetMapper.getAppKey(faceSetId).getAppkey();
        String name = facesetMapper.getAppKey(faceSetId).getName();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            在人脸库中加入人脸
        for (int i = 0; i < files.length; i++) {
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date();
            String format1 = sdf2.format(date);
            CommonJsonObjectResponse faceToFile = aiFaceBody.createFaceToFile(faceSetId, files[i].getBytes(),faceName,faceInfo,appKey,null);
            System.out.println(faceToFile.getCommonResult()+"111111");
            Object faceId = faceToFile.getCommonResult().get("faceId");
            String filename = files[i].getOriginalFilename();
//            数据库更新(allimgs//humans)
            allimgmapper.updateallimg(files[i].getOriginalFilename(),sdf.format(date),files[i].getSize(),"human",faceInfo,
                    "D:\\1rjb\\webphoto2\\static\\smart_photos\\allimgs\\"+master+format1+ filename,
                    master,"123456",master+format1+ filename,faceId.toString());
            humanmapper.addhumanInfo(files[i].getOriginalFilename(),sdf.format(date),files[i].getSize(),"human",
                    faceInfo,"D:\\1rjb\\webphoto2\\static\\smart_photos\\humans\\"+master+format1+ filename,
                    master,"123456",master+format1+ filename,faceSetId+"",faceId.toString());
//            文件上传到对应的数据库中
            File dest = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\allimgs\\"+master+format1 + filename);
            File dest1 = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\humans\\" +master+format1+ filename);

            files[i].transferTo(dest);
            files[i].transferTo(dest1);


        }

        return "success";
    }



}
