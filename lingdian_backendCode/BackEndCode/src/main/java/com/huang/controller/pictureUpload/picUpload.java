package com.huang.controller.pictureUpload;

import com.huang.mapper.*;
import com.huang.pojo.pathInfo.storepath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Description 图片上传到云端||这个模块的功能需要废弃掉
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */
//这个模块的图片上传需要进行修改！！！
@RestController
public class picUpload {

    @Autowired
    storepathmapper storepathmapper;
    @Autowired
    animalmapper animalmapper;
    @Autowired
    constructmapper constructmapper;
    @Autowired
    com.huang.mapper.faceMapper.humanmapper humanmapper;
    @Autowired
    vehiclemapper vehiclemapper;
    @Autowired
    allimgmapper allimgmapper;
    @Autowired
    scenerymapper scenerymapper;
    @Autowired
    othermapper othermapper;

    @RequestMapping("/picupload")
    public Object upload(@RequestParam("files") MultipartFile[] files,
                         @RequestParam("user") String master,
                         @RequestParam("details") String details,
                         @RequestParam("type") String type) {
        System.out.println("进来了");
        System.out.println(files.length);
        if (files[0].isEmpty()) {
            return "fail";
        }
        HashMap<String, String> paths = new HashMap<>();
        List<storepath> storepaths = storepathmapper.querystorepaths();
        for (int i = 0; i < storepaths.size(); i++) {
            paths.put(storepaths.get(i).getImgType(), storepaths.get(i).getStorePath());
        }
        String types[] = {"", "human", "animal", "scenery", "vehicle", "construct", "other"};      // 1 2 3 4 5 6
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        for (int i = 0; i < files.length; i++) {
            String path = paths.get(types[Integer.parseInt(type)]); //获取指定类别照片的存储地址
            System.out.println(path);
            Date date1 = new Date();
            String date = sdf.format(date1);
            String format = sdf1.format(date1);
//            数据库信息更新-
            if (type.equals("1")) {
                humanmapper.addhumanInfo(files[i].getOriginalFilename().replace(",",""), date, files[i].getSize(),
                        "human", details, "D:\\1rjb\\webphoto2\\static\\smart_photos\\humans\\" +master+format+ files[i].getOriginalFilename().replace(",",""), master, "123456", master+format+files[i].getOriginalFilename().replace(",",""),"","");
            }
            if (type.equals("2")) {
                animalmapper.addanimalInfo(files[i].getOriginalFilename().replace(",",""), date, files[i].getSize(),
                        "animal", details, "D:\\1rjb\\webphoto2\\static\\smart_photos\\animals\\" +master+format+ files[i].getOriginalFilename().replace(",",""), master, "123456", master+format+files[i].getOriginalFilename().replace(",",""));
            }
            if (type.equals("3")) {
                scenerymapper.addsceneryInfo(files[i].getOriginalFilename().replace(",",""), date, files[i].getSize(),
                        "scenery", details, "D:\\1rjb\\webphoto2\\static\\smart_photos\\sceneries\\"  +master+format+ files[i].getOriginalFilename().replace(",",""), master, "123456", master+format+files[i].getOriginalFilename().replace(",",""));
            }
            if (type.equals("4")) {
                vehiclemapper.addvehicleInfo(files[i].getOriginalFilename().replace(",",""), date, files[i].getSize(),
                        "vehicle", details, "D:\\1rjb\\webphoto2\\static\\smart_photos\\vehicles\\" +master+format + files[i].getOriginalFilename().replace(",",""), master, "123456", master+format+files[i].getOriginalFilename().replace(",",""));
            }
            if (type.equals("6")) {
                othermapper.addotherInfo(files[i].getOriginalFilename().replace(",",""), date, files[i].getSize(),
                        "other", details, "D:\\1rjb\\webphoto2\\static\\smart_photos\\others\\" +master+format + files[i].getOriginalFilename().replace(",",""), master, "123456", master+format+files[i].getOriginalFilename().replace(",",""));
            }
            if (type.equals("5")) {
                constructmapper.addconstructInfo(files[i].getOriginalFilename().replace(",",""), date, files[i].getSize(),
                        "construct", details, "D:\\1rjb\\webphoto2\\static\\smart_photos\\constructs\\"  +master+format+ files[i].getOriginalFilename().replace(",",""), master, "123456", master+format+files[i].getOriginalFilename().replace(",",""));
            }
            allimgmapper.updateallimg(files[i].getOriginalFilename().replace(",",""), date, files[i].getSize(),
                    types[Integer.valueOf(type)], details, "D:\\1rjb\\webphoto2\\static\\smart_photos\\allimgs\\"  +master+format+ files[i].getOriginalFilename().replace(",",""), master, "123456", master+format+files[i].getOriginalFilename().replace(",",""),"");
            //循环将图片上传到指定位置
            String filename = files[i].getOriginalFilename().replace(",","");
            String filepath = path + "\\";     //这里不加对不加两个“\\”的话，就是同级目录了！
            File dest = new File(filepath +master+format+ filename);
            File dest2 = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\allimgs\\" +master+format+ filename);
            System.out.println(filepath);
            try {
                files[i].transferTo(dest);
                files[i].transferTo(dest2);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }


}
