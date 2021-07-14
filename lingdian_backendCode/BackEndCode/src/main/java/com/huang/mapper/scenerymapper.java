package com.huang.mapper;

import com.huang.pojo.typesModule.scenery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description 风景类的操作mapper
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */

@Mapper
@Repository
//风景类
public interface scenerymapper {
    //    增加一个风景类照片
    void addsceneryInfo(String imgName, String uploadTime, double imgSize, String imgType,
                        String details, String imgPath, String master, String md5, String name);

    //    查询风景类的所有照片
    List<scenery> querysceneries(String master);

    void delsceneryimg(String master,String name);

}
