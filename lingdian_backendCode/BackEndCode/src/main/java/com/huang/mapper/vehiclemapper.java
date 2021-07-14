package com.huang.mapper;

import com.huang.pojo.typesModule.vehicle;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @Description 交通工具类的操作mapper
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */

@Mapper
@Repository
//交通工具类
public interface vehiclemapper {
//    增加一个交通工具类照片
    void addvehicleInfo(String imgName, String uploadTime, double imgSize, String imgType,
                        String details, String imgPath, String master, String md5,String name);
//    查询交通工具类的所有照片
    List<vehicle> queryvehicles(String master);

    void delvehicleimg(String master,String name);

}
