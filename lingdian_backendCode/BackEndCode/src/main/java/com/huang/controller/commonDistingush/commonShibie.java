package com.huang.controller.commonDistingush;

import com.huang.mapper.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

/**
 * @Description 通用图片识别工具类
 * @Author huangyang
 * @date 2021年06月17日 20:56
 */
public class commonShibie {

    public HashMap<Integer, String> commonShibie(MultipartFile[] files) {
        String[] info = new String[files.length];
        System.out.println(files.length);
        String result[] = new String[files.length];
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
        }
        System.out.println(Arrays.toString(info));          //小类
        System.out.println(Arrays.toString(result));        //大类
        HashMap<Integer, String> val = new HashMap<Integer, String>();
        for (int i = 0; i < files.length; i++) {
            val.put(i, result[i] + "/" + info[i]);
        }

        return val;
    }


}
