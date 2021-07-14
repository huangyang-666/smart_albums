package com.huang.controller.deleteImgs;

import com.chinamobile.bcop.api.sdk.ai.core.exception.ClientException;
import com.chinamobile.bcop.api.sdk.ai.core.model.CommonJsonObjectResponse;
import com.chinamobile.bcop.api.sdk.ai.facebody.AiFaceBody;
import com.huang.controller.faceDistingush.FaceAi;
import com.huang.mapper.allimgmapper;
import com.huang.mapper.faceMapper.facesetMapper;
import com.huang.mapper.faceMapper.humanmapper;
import com.huang.pojo.faceModule.human;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * @Description    在人脸库中删除人脸照片
 * @Author huangyang
 * @date 2021年06月15日 21:25
 */

@RestController
public class deleteFace {

    @Autowired
    humanmapper humanmapper;
    @Autowired
    com.huang.mapper.allimgmapper allimgmapper;
    @Autowired
    facesetMapper facesetmapper;

    @RequestMapping("/deleteface")
    public Object deleteface(@RequestParam("username")String master,
                             @RequestParam("filenames")String[] filenames) throws ClientException {     //解决faceId的获取问题-->现在解决faceId的获取问题
//        删除人脸照片---->删除数据库信息(类别、所有图片)&&删除图片文件(类别、所有图片)&&删除api人脸库信息
        for (String filename : filenames) {
            human face = humanmapper.queryfaceByname(master, filename);
            //        数据库信息删除
            allimgmapper.delcommonimg(master,filename);
            humanmapper.deleteface(master,face.getFaceSet(),face.getFaceId());
            //        api信息库删除
            AiFaceBody instance = FaceAi.getInstance();
            String appkey = facesetmapper.getAppKey(face.getFaceSet()).getAppkey();
            CommonJsonObjectResponse response = instance.deleteFace(Integer.valueOf(face.getFaceSet().replace(".0", "")),
                    Integer.valueOf(face.getFaceId().replace(".0","")), appkey, null);
            //        文件夹目录删除
            File dest = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\allimgs\\" + filename);
            File dest1 = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\humans\\" + filename);
            dest.delete();          //全部文件夹中的文件删除
            dest1.delete();         //人物文件夹中的文件删除

        }

        return "success";
    }



}
