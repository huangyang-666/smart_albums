package com.huang.mapper;

import com.huang.pojo.showtimeModule.showtime;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @Description “发现”模块的操作mapper
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */

@Mapper
@Repository
//发现===>精彩时刻
public interface showtimemapper {
//新增一个用户的精彩时刻
    void addshowtime(String master,String name,String imgPath,String mood,String date);
//查询所有用户的精彩时刻
    List<showtime> queryallshowtime();

    List<showtime> queryshowtimeBymood(String keyword);


}
