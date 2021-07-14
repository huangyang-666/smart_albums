package com.huang.controller.download;

import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Description   第三个接口--->下载excel报表
 * @Author huangyang
 * @date 2021年06月20日 13:50
 */
@RestController
public class downloadexcel {

    @RequestMapping("/getExcelDownload")
    public Object getExcelDownload(@RequestParam("user") String master,
                                   HttpServletRequest req,
                                   HttpServletResponse resp) throws IOException {
        //            获取下载文件的名字
        System.out.println(master);
        String filename=master+"outcome.xls";
        String filepath="D:\\1rjb\\webphoto2\\static\\smart_photos\\download\\"+master+"outcome.xls";
        //            设置浏览器支持的响应头
        resp.setContentType("application/vnd.ms-excel");
        resp.setHeader("Content-Disposition", "attachment;filename=" + filename.replace(".xls","")+".xls");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        //            获取下载文件的输入流
        File targetFile = new File(filepath);
        FileInputStream in = new FileInputStream(targetFile.getPath());
        //            创建缓冲区
        int len = 0;
        byte[] buffer = new byte[1024];
//            获取OutPutStream输出对象
        ServletOutputStream out = resp.getOutputStream();
//            将FileOutputStream流写入到buffer缓冲区，使用OutPutStream将缓冲区中的数据输出到客户端
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();

        return "success";
    }


}
