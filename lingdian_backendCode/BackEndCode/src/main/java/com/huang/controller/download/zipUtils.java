package com.huang.controller.download;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * @Description 文件压缩工具类
 * @Author huangyang
 * @date 2021年06月17日 17:32
 */

public class zipUtils {

    public static void zipFile(File sourceFile, ZipOutputStream zipOutputStream) throws IOException {
        // 文件夹，则递归遍历
        if (sourceFile.isDirectory()) {
            File[] files = sourceFile.listFiles();
            if (files == null) {
                return;
            }
            for (File subFile : files) {
                zipFile(subFile, zipOutputStream);
            }
        }
        // 文件，写入文件
        if (sourceFile.isFile()) {
            // 获取父路径
            String regx = "picdownload";
            int index = sourceFile.getPath().indexOf(regx);
            String fileName = sourceFile.getPath().substring(index + regx.length() + 1);
            zipOutputStream.putNextEntry(new ZipEntry(fileName));
            // 写入数据
            int temp;
            InputStream inputStream = new FileInputStream(sourceFile);
            while ((temp = inputStream.read()) != -1) {
                zipOutputStream.write(temp);
            }
            inputStream.close();
        }
    }


}





