package com.huang.controller.showtimecontoller;

import com.huang.mapper.showtimemapper;
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
 * @Description 增加一条”发现动态“
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */

@RestController
public class addshowtime {

    @Autowired
    showtimemapper showtimemapper;

    @RequestMapping("/addshowtime")
    public String addshowtime(@RequestParam("user") String master,
                               @RequestParam("imgFile")MultipartFile file,
                               @RequestParam("mood") String mood) throws IOException {
//        1.将照片存储到指定文件目录下
        File dest = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\discovery");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        String format = sdf.format(date);
        String format1 = sdf1.format(date);
        file.transferTo(new File(dest + "\\" + master+format + file.getOriginalFilename()));
//        添加数据库信息
        showtimemapper.addshowtime(master,master+format+file.getOriginalFilename(),dest + "\\" + format + file.getOriginalFilename(),mood,format1);
        System.out.println("动态添加成功！"+master+"==>"+file.getOriginalFilename()+"==>"+mood);
        return "success";
    }

}
