package com.huang.controller.displaycontroller.DeleteAndDownloadModule;

import com.huang.mapper.allimgmapper;
import com.huang.pojo.typesModule.commonimg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description 批量删除、下载页面的全部照片信息
 * @Author huangyang
 * @date 2021年06月21日 1:19
 */
@RestController
public class showallimgsinfo {

    @Autowired
    com.huang.mapper.allimgmapper allimgmapper;

    @RequestMapping("/showallimgInfo")
    public List<commonimg> showallimgsInfo(@RequestParam("user")String master){
        System.out.println("hello!!!!");
        List<commonimg> allimginfos = allimgmapper.queryallimgs(master);
        return allimginfos;
    }


}
