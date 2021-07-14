package com.huang.mapper;

import com.huang.pojo.typesModule.animal;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @Description 动物类的操作mapper
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */

@Mapper
@Repository
//动物类别
public interface animalmapper {
//    增加一个动物照片
    void addanimalInfo(String imgName, String uploadTime, double imgSize, String imgType,
                       String details, String imgPath, String master, String md5,String name);
//    查询动物类的所有照片
    List<animal> queryanimals(String master);
//    删除动物照片
    void deleteanimalimg(String master,String name);

}
