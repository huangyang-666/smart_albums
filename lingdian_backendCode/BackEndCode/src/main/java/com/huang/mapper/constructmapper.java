package com.huang.mapper;

import com.huang.pojo.typesModule.construct;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @Description 建筑类的操作mapper
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */

@Mapper
@Repository
//建筑类
public interface constructmapper {
//    增加一个建筑类照片
    void addconstructInfo(String imgName, String uploadTime, double imgSize, String imgType,
                          String details, String imgPath, String master, String md5,String name);
//    查询建筑类的所有照片
    List<construct> queryconstructs(String master);
//删除某个照片
    void delconstructimg(String master,String name);

}
