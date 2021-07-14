package com.huang.mapper;

import com.huang.pojo.typesModule.animal;
import com.huang.pojo.typesModule.commonimg;
import com.huang.pojo.typesModule.other;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 所有图片的操作mapper
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */

@Mapper
@Repository
//所有照片的操作
public interface othermapper {
    //    增加一个动物照片
    void addotherInfo(String imgName, String uploadTime, double imgSize, String imgType,
                       String details, String imgPath, String master, String md5,String name);
    //    查询动物类的所有照片
    List<other> queryothers(String master);

    void delothertimg(String master,String name);


}
