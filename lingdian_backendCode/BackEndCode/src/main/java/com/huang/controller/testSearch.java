package com.huang.controller;

import com.chinamobile.cmss.sdk.ECloudDefaultClient;
import com.chinamobile.cmss.sdk.IECloudClient;
import com.chinamobile.cmss.sdk.http.constant.Region;
import com.chinamobile.cmss.sdk.http.signature.Credential;
import com.chinamobile.cmss.sdk.request.EngineImageClassifyDetectPostRequest;
import com.chinamobile.cmss.sdk.response.EngineImageClassifyDetectResponse;
import com.chinamobile.cmss.sdk.response.bean.EngineClassify;
import com.chinamobile.cmss.sdk.util.JacksonUtil;
import com.huang.mapper.allimgmapper;
import com.huang.mapper.showtimemapper;
import com.huang.pojo.typesModule.commonimg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description
 * @Author huangyang
 * @date 2021年05月30日 13:10
 */
@RestController
public class testSearch {

    @Autowired
    com.huang.mapper.allimgmapper allimgmapper;

    @RequestMapping("/test")
    public Object test(@RequestParam("user")String master,
                       @RequestParam("keywords")String keywords) throws IOException {
        List<commonimg> results = allimgmapper.queryImgInfoBykeyWords(master, keywords);
        return results;
    }


}
