package com.huang.controller.displaycontroller;

import com.huang.mapper.*;
import com.huang.pojo.pathInfo.storepath;
import com.huang.pojo.typesModule.commonimg;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @Description 编辑照片信息
 * @Author huangyang
 * @date 2021年06月23日 13:54
 */
@RestController
public class UpdateImgInfos {
    @Autowired
    com.huang.mapper.animalmapper animalmapper;
    @Autowired
    com.huang.mapper.constructmapper constructmapper;
    @Autowired
    com.huang.mapper.faceMapper.humanmapper humanmapper;
    @Autowired
    com.huang.mapper.vehiclemapper vehiclemapper;
    @Autowired
    com.huang.mapper.allimgmapper allimgmapper;
    @Autowired
    com.huang.mapper.scenerymapper scenerymapper;
    @Autowired
    othermapper othermapper;
    @Autowired
    storepathmapper storepathmapper;


    @RequestMapping("/updateImgInfos")
    public Object updateimgInfos(@RequestParam("user") String master,
                                 @RequestParam("type") String type,
                                 @RequestParam("filename") String filename,
                                 @RequestParam("details") String details) throws IOException {
//信息更新的一般思路---->
// 1.修改allimgs信息（type&filename）
        System.out.println(master + "   " + type + "  " + filename + "  " + details);
// 2.修改大类中的信息(在原本的类中删除这条信息，在另外对应的中加入这个信息)
        commonimg commonimg = allimgmapper.queryImgInfoByName(filename, master);        //等会做处理取值
//        删除原来类别中的信息
        String originType = commonimg.getImgType();
        if (originType.equals("human")) {
            humanmapper.deletehumanImg(master, filename);
        }
        if (originType.equals("animal")) {
            animalmapper.deleteanimalimg(master, filename);
        }
        if (originType.equals("construct")) {
            constructmapper.delconstructimg(master, filename);
        }
        if (originType.equals("vehicle")) {
            vehiclemapper.delvehicleimg(master, filename);
        }
        if (originType.equals("scenery")) {
            scenerymapper.delsceneryimg(master, filename);
        }
        if (originType.equals("other")) {
            othermapper.delothertimg(master, filename);
        }
//            删除原来文件中的照片
//        File file = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\" + commonimg.getImgType() + "s\\" + filename);
        storepath storepath1 = storepathmapper.queryPathByType(commonimg.getImgType());
        File file = new File(storepath1.getStorePath() + "\\" + filename);
        System.out.println("地址！"+file.getPath());
//      更新数据库信息
        allimgmapper.updateImgInfos(master, filename, details, type);
        if (type.equals("animal")) {
            animalmapper.addanimalInfo(commonimg.getImgName(), commonimg.getUploadTime(), commonimg.getImgSize(),
                    type, details, "D:\\1rjb\\webphoto2\\static\\smart_photos\\" + type + "s\\" + filename, master, "123456", filename);
        }
        if (type.equals("construct")) {
            constructmapper.addconstructInfo(commonimg.getImgName(), commonimg.getUploadTime(), commonimg.getImgSize(),
                    type, details, "D:\\1rjb\\webphoto2\\static\\smart_photos\\" + type + "s\\" + filename, master, "123456", filename);
        }
        if (type.equals("vehicle")) {
            vehiclemapper.addvehicleInfo(commonimg.getImgName(), commonimg.getUploadTime(), commonimg.getImgSize(),
                    type, details, "D:\\1rjb\\webphoto2\\static\\smart_photos\\" + type + "s\\" + filename, master, "123456", filename);
        }
        if (type.equals("scenery")) {
            scenerymapper.addsceneryInfo(commonimg.getImgName(), commonimg.getUploadTime(), commonimg.getImgSize(),
                    type, details, "D:\\1rjb\\webphoto2\\static\\smart_photos\\" + type + "s\\" + filename, master, "123456", filename);
        }
        if (type.equals("human")) {
            humanmapper.addhumanInfo(commonimg.getImgName(), commonimg.getUploadTime(), commonimg.getImgSize(),
                    type, details, "D:\\1rjb\\webphoto2\\static\\smart_photos\\" + type + "s\\" + filename, master, "123456", filename, "", "");
        }
        if (type.equals("other")) {
            othermapper.addotherInfo(commonimg.getImgName(), commonimg.getUploadTime(), commonimg.getImgSize(),
                    type, details, "D:\\1rjb\\webphoto2\\static\\smart_photos\\" + type + "s\\" + filename, master, "123456", filename);
        }
//        生成新的文件
        storepath storepath = storepathmapper.queryPathByType(type);
        File dest = new File(storepath.getStorePath()+"\\" + filename);
        File file1 = new File(commonimg.getImgPath());
        MultipartFile endfile = null;
        FileInputStream inputStream = new FileInputStream(file1);
        endfile = new MockMultipartFile(file1.getName(), file1.getName(),
                ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
        endfile.transferTo(dest);
        file.delete();
        return "success";
    }


}
