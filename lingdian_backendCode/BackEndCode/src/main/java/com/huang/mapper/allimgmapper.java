package com.huang.mapper;

import com.huang.pojo.typesModule.commonimg;
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
public interface allimgmapper {
//更新照片库
    void updateallimg(String imgName, String uploadTime, double imgSize, String imgType,
                       String details, String imgPath, String master, String md5,String name,String faceId);
//    查询某个用户上传的所有照片
    List<commonimg> queryallimgs(String master);
//    删除某个人脸照片
    void delimg(String master,String faceSet,String faceId);
//    为人脸照片更新faceId
    void updateFaceId(String master,String faceStoreId,String imgName,String faceId);
//    删除某个人脸库下的所有照片
    void delfaces(String master,String faceStoreId);
//    删除某个普通类照片
    void delcommonimg(String master,String name);
//    通过名字查询照片信息
    commonimg queryImgInfoByName(String name,String master);
//    通过关键词搜索照片
    List<commonimg> queryImgInfoBykeyWords(String master,String keywords);
//    更新照片信息(details    imgType)
    void updateImgInfos(String master,String name,String details,String imgType);


}
