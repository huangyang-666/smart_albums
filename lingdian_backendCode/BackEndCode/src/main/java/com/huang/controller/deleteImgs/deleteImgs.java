package com.huang.controller.deleteImgs;

import com.huang.mapper.*;
import com.huang.mapper.faceMapper.humanmapper;
import com.huang.pojo.typesModule.commonimg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @Description    任意类别照片的删除操作
 * @Author huangyang
 * @date 2021年06月19日 13:17
 */

@RestController
public class deleteImgs {
    @Autowired
    com.huang.mapper.allimgmapper allimgmapper;
    @Autowired
    com.huang.mapper.animalmapper animalmapper;
    @Autowired
    com.huang.mapper.constructmapper constructmapper;
    @Autowired
    othermapper othermapper;
    @Autowired
    scenerymapper scenerymapper;
    @Autowired
    vehiclemapper vehiclemapper;
    @Autowired
    humanmapper humanmapper;


    @RequestMapping("/deleteImgs")
    public Object deleteimgs(@RequestParam("user")String master,
                             @RequestParam("filenames")String[] filenames){
//        名字在所有数据库中都是唯一的---->将名字作为删除的
        System.out.println(filenames.length);
        for (String filename : filenames) {
            if (filename.equals("")){
                continue;
            }
            filename = filename.replace("undefined", "");
            System.out.println(filename);
            commonimg commonimg = allimgmapper.queryImgInfoByName(filename,master);
            String type=commonimg.getImgType();
            System.out.println(type);
            if (type.equals("human")){
                //        删除数据库信息
                humanmapper.deletehumanImg(master,filename);
                allimgmapper.delcommonimg(master,filename);
                //        删除文件信息
                File file = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\allimgs\\" + filename);
                File file1 = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\humans\\" + filename);
                file.delete();
                file1.delete();
            }
            if (type.equals("animal")){
                //        删除数据库信息
                animalmapper.deleteanimalimg(master,filename);
                allimgmapper.delcommonimg(master,filename);
                //        删除文件信息
                File file = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\allimgs\\" + filename);
                File file1 = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\animals\\" + filename);
                file.delete();
                file1.delete();
            }
            if (type.equals("construct")){
                //        删除数据库信息
                allimgmapper.delcommonimg(master,filename);
                constructmapper.delconstructimg(master,filename);
                //        删除文件信息
                File file = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\allimgs\\" + filename);
                File file1 = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\constructs\\" + filename);
                file.delete();
                file1.delete();
            }
            if (type.equals("other")){
                //        删除数据库信息
                allimgmapper.delcommonimg(master,filename);
                vehiclemapper.delvehicleimg(master,filename);
                //        删除文件信息
                File file = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\allimgs\\" + filename);
                File file1 = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\others\\" + filename);
                file.delete();
                file1.delete();
            }
            if (type.equals("scenery")){
                //        删除数据库信息
                allimgmapper.delcommonimg(master,filename);
                vehiclemapper.delvehicleimg(master,filename);
                //        删除文件信息
                File file = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\allimgs\\" + filename);
                File file1 = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\sceneries\\" + filename);
                file.delete();
                file1.delete();
            }
            if (type.equals("vehicle")){
                //        删除数据库信息
                allimgmapper.delcommonimg(master,filename);
                vehiclemapper.delvehicleimg(master,filename);
                //        删除文件信息
                File file = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\allimgs\\" + filename);
                File file1 = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\vehicles\\" + filename);
                file.delete();
                file1.delete();
            }

        }
        System.out.println("结束了");
        return "success";
    }


}
