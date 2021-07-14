package com.huang.controller.showtimecontoller;

import com.huang.mapper.showtimemapper;
import com.huang.pojo.showtimeModule.showtime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description 根据传入的关键词查询某些“发现时刻”
 * @Author huangyang
 * @date 2021年05月30日 13:31
 */

public class queryshowtimesBykeyword {
    @Autowired
    showtimemapper showtimemapper;

    @RequestMapping("/queryspecialtimes")
    public String specialtimes(String keyword) {
        List<showtime> showtimes = showtimemapper.queryshowtimeBymood(keyword);
        System.out.println(showtimes.size());
        for (int i = 0; i < showtimes.size(); i++) {
            System.out.println(showtimes.get(i));
        }
        return "ok";
    }
}
