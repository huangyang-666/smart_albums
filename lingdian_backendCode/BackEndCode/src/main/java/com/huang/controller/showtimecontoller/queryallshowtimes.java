package com.huang.controller.showtimecontoller;

import com.huang.pojo.showtimeModule.showtime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description 展示全部”发现“动态
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */

@RestController
public class queryallshowtimes {

    @Autowired
    com.huang.mapper.showtimemapper showtimemapper;

    @RequestMapping("/queryallshowtimes")
    public List<showtime> allshowtimes(@RequestParam("user")String master) {
        List<showtime> showtimes = showtimemapper.queryallshowtime();
        return showtimes;
    }


}
