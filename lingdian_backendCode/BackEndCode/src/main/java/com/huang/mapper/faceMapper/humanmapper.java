package com.huang.mapper.faceMapper;

import com.huang.pojo.faceModule.human;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @Description 人物类的操作mapper
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */

@Mapper
@Repository
//人物类
public interface humanmapper {
//    增加一个任务类照片
    void addhumanInfo(String imgName, String uploadTime, double imgSize, String imgType, String details,
                      String imgPath, String master, String md5,String name,String faceSet,String faceId);
//    查询人物类的所有照片
    List<human> queryhumans(String master);
//    删除人脸
    void  deleteface(String master,String faceSet,String faceId);
//    删除某个人脸库下的所有人脸照片
    void delfaces(String master,String faceSet);
//    查询某个人脸库下的所有照片
    List<human> queryfaces(String master,String faceStoreId);
//    查询某个人脸的信息
    human queryfaceByname(String master,String name);
//    批量删除
    void deletehumanImg(String master,String name);




}
