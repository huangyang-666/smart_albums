package com.huang;

import com.huang.mapper.allimgmapper;
import com.huang.mapper.animalmapper;
import com.huang.mapper.faceMapper.facesetMapper;
import com.huang.mapper.showtimemapper;
import com.huang.pojo.faceModule.result;
import com.huang.service.UserService;
import com.huang.service.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
import java.io.File;
import java.io.FilePermission;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
@RestController
class LasttestApplicationTests {

    @Autowired
    UserServiceImpl userService;

    @Test
    void contextLoads() throws IOException {
        System.out.println(userService.queryUserByname("huangyang"));
    }

}
