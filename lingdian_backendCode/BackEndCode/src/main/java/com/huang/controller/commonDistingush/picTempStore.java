package com.huang.controller.commonDistingush;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 智能识别第一个接口---》接收前端传递的文件，并存储到他的位置
 * 这里防止，高用户并发时发生的文件混乱，我们设置命名规则---》D:\1rjb\fileTempStorePath\master\+(所有的文件)
 * @Author huangyang
 * @date 2021年06月22日 21:24
 */

@RestController
public class picTempStore {

    @RequestMapping("/picTempStore")
    public List<String> TempStorePics(@RequestParam("user") String master,
                                      @RequestParam("files") MultipartFile[] files) throws IOException {
//        接收全部的文件并上传到固定设置的位置
        //1.生成用户的智能识别接口存放图片文件的位置
//        D:\1rjb\webphoto2\static
        File storePath = new File("D:\\1rjb\\webphoto2\\static\\fileTempStorePath\\" + master);
        if (!storePath.isDirectory()) {
            storePath.mkdirs();
        }
        File[] prefiles = storePath.listFiles();
        for (int i = 0; i < prefiles.length; i++) {
            prefiles[i].delete();
        }
        //2.将文件都放进存储位
        ArrayList<String> res = new ArrayList<>();
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename().replace(",",""); //获取当前文件的初始名字 删除名字中携带的逗号
            res.add(filename);
            File dest = new File(storePath + "\\" + filename);
            System.out.println(dest.getPath());     //输出验证当前文件的存放位置
//            将文件上传
            file.transferTo(dest);
        }
        return res;
    }


}
