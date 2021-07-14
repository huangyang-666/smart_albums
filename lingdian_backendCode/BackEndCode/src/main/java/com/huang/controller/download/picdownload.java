package com.huang.controller.download;

import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipOutputStream;

import static com.huang.controller.download.zipUtils.zipFile;

/**
 * @Description 文件批量下载
 * @Author huangyang
 * @date 2021年06月17日 13:38
 */
@RestController
public class picdownload {

    @PostMapping("/picdownload")
    public void picdownload(@RequestParam("filenames") String[] filenames,
                            @RequestParam("user") String master,
                            HttpServletResponse resp) throws IOException {
//        思路，先将图片都转移到一个文件夹中，然后再让用户下载这个文件夹
//        先将文件都上传到这个文件夹
        System.out.println(master);
        for (String filename : filenames) {
            System.out.println("name===>"+filename);
        }
        resp.reset();
        System.out.println(filenames.length);
        Date date = new Date();
//        String master="admin";
//        String[] filenames={""};
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(date);
        File picstorepath = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\download\\" + master + format + "picdownload");
        if (!picstorepath.isDirectory()) {
            picstorepath.mkdirs();
        }
        MultipartFile tempfile = null;
        FileInputStream fileInputStream = null;
        for (String filepath : filenames) {
            if (!filepath.equals("")) {
                String newname = filepath.replace("undefined", "");
                System.out.println(newname);
                File file = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\allimgs\\" + newname);
                System.out.println(file.getPath());
                fileInputStream = new FileInputStream(file);
                tempfile = new MockMultipartFile(file.getName(), file.getName(),
                        ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
                File dest = new File(picstorepath.getPath() + "\\" + filepath.substring(filepath.lastIndexOf("\\") + 1));
                tempfile.transferTo(dest);
            }
        }

//        将文件夹变成压缩包
        String targetPath = "D:\\1rjb\\webphoto2\\static\\smart_photos\\download\\" + master + format + "picdownload" + ".zip";
        File targetFile = new File(targetPath);
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(targetFile));
        zipFile(picstorepath, zipOut);
        zipOut.close();

//          实现下载
//            获取下载文件的名字
        String filename = targetFile.getPath().substring(picstorepath.getPath().lastIndexOf("\\") + 1);
        System.out.println(filename);
//            设置浏览器支持的响应头
        resp.setContentType("multipart/form-data");
        resp.setHeader("Content-Disposition", "attachment;filename=" + filename);
        resp.setHeader("Access-Control-Allow-Origin", "*");

//            获取下载文件的输入流
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
        System.out.println("结束了");


    }


}
