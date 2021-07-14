package com.huang.controller.commonDistingush;

import com.huang.mapper.*;
import com.huang.pojo.pathInfo.storepath;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Description 通用图像识别&&图片上传&&6.15这里删除了对人脸照片的处理&&有了人脸检测模块，这里对所有图片的添加操作删除
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */

@RestController
public class commonDistingush {

    @Autowired
    animalmapper animalmapper;
    @Autowired
    constructmapper constructmapper;
    @Autowired
    vehiclemapper vehiclemapper;
    @Autowired
    allimgmapper allimgmapper;
    @Autowired
    scenerymapper scenerymapper;
    @Autowired
    storepathmapper storepathmapper;

    @RequestMapping("/common")
    public String distinguish(@RequestParam("files") MultipartFile[] files,
                              @RequestParam("user") String master,
                              @RequestParam("details") String details, HttpServletResponse response) {
        String[] info = new String[files.length];
        System.out.println(files.length);
        String result[] = new String[files.length];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss"); //设置时间标准
        Date date1 = new Date();
        String date = sdf.format(date1);
        for (int i = 0; i < files.length; i++) {
            byte[] data = null;
            InputStream in = null;
            try {
                in = files[i].getInputStream();
                data = new byte[in.available()];
                in.read(data);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //对字节数组进行base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            String tpjiema = encoder.encode(data);
            info[i] = pictureShibie.tongyongshibie(tpjiema);
            //        在这里获取图片的大类信息（模拟假数据）
            String type;
//            如果不包含某个字段，那就设置一个无穷大的数，来防止干扰
            int animalVal = (info[i].indexOf("动物") == -1) ? 1000 : info[i].indexOf("动物");
            int sceneryVal = (info[i].indexOf("自然") == -1) ? 1000 : info[i].indexOf("自然");
            int constructVal = (info[i].indexOf("建筑") == -1) ? 1000 : info[i].indexOf("建筑");
            int vehicleVal = (info[i].indexOf("交通工具") == -1) ? 1000 : info[i].indexOf("交通工具");
            if (animalVal < sceneryVal && animalVal < constructVal && animalVal < vehicleVal) {
                type = "animal";
            } else if (sceneryVal < animalVal && sceneryVal < constructVal && sceneryVal < vehicleVal) {
                type = "scenery";
            } else if (constructVal < animalVal && constructVal < sceneryVal && constructVal < vehicleVal) {
                type = "construct";
            } else if (vehicleVal < animalVal && vehicleVal < constructVal && vehicleVal < sceneryVal) {
                type = "vehicle";
            } else {
                type = "other";
            }


            result[i] = type;
            System.out.println("type===>" + type);
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            String date0 = sdf2.format(new Date());
//            上传照片信息添加进数据库
            if (type.equals("animal")) {
                animalmapper.addanimalInfo(info[i], date0, files[i].getSize(),
                        "animal", details, "C:\\Users\\26547\\Desktop\\temp\\webphoto2\\static\\smart_photos\\animals\\" + master + date + files[i].getOriginalFilename(), master, "123456", master + date + files[i].getOriginalFilename());
            }
            if (type.equals("scenery")) {
                scenerymapper.addsceneryInfo(info[i], date0, files[i].getSize(),
                        "scenery", details, "C:\\Users\\26547\\Desktop\\temp\\webphoto2\\static\\smart_photos\\sceneries\\" + master + date + files[i].getOriginalFilename(), master, "123456", master + date + files[i].getOriginalFilename());
            }
            if (type.equals("vehicle")) {
                vehiclemapper.addvehicleInfo(info[i], date0, files[i].getSize(),
                        "vehicle", details, "C:\\Users\\26547\\Desktop\\temp\\webphoto2\\static\\smart_photos\\vehicles\\" + master + date + files[i].getOriginalFilename(), master, "123456", master + date + files[i].getOriginalFilename());
            }
            if (type.equals("construct")) {
                constructmapper.addconstructInfo(info[i], date0, files[i].getSize(),
                        "construct", details, "C:\\Users\\26547\\Desktop\\temp\\webphoto2\\static\\smart_photos\\constructs\\" + master + date + files[i].getOriginalFilename(), master, "123456", master + date + files[i].getOriginalFilename());
            }
            allimgmapper.updateallimg(info[i], date0, files[i].getSize(),
                    type, details, "C:\\Users\\26547\\Desktop\\temp\\webphoto2\\static\\smart_photos\\allimgs\\" + master + date + files[i].getOriginalFilename(), master, "123456", master + date + files[i].getOriginalFilename(), "");
        }
        System.out.println(Arrays.toString(info));
        System.out.println(Arrays.toString(result));
//        下面进行图片的上传工作，需要的参数文件流files, 结果集映射识别出来的名字info[]，大类结果集映射result[]
        HashMap<String, String> paths = new HashMap<>();
        List<storepath> storepaths = storepathmapper.querystorepaths();
        for (int i = 0; i < storepaths.size(); i++) {
            paths.put(storepaths.get(i).getImgType(), storepaths.get(i).getStorePath());
        }
        for (int i = 0; i < files.length; i++) {
            String path = paths.get(result[i]);  //获取指定类别照片的存储地址
            System.out.println(path);
            //循环将图片上传到指定位置
            String filename = files[i].getOriginalFilename();
            String filepath = path + "\\";     //这里不加对不加两个“\\”的话，就是同级目录了！
            File dest = new File(filepath + master + date + filename);
            File dest2 = new File("C:\\Users\\26547\\Desktop\\temp\\webphoto2\\static\\smart_photos\\allimgs\\" + master + date + filename);
            try {
                FileUtils.copyInputStreamToFile(files[i].getInputStream(), dest);
                //      所有文件的目录下上传新的照片
                FileUtils.copyInputStreamToFile(files[i].getInputStream(), dest2);
                System.out.println(dest.getPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//      返回前端需要传递的结果
        if (String.valueOf(response.getStatus()).equals("200")) {    //根据浏览器的当前状态确定res
            return "success";
        } else {
            return "fail";
        }
    }

}
