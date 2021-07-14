package com.huang.mapper.faceMapper;

import com.huang.pojo.faceModule.faceset;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author huangyang
 * @date 2021年06月13日 18:06
 */
@Mapper
@Repository
public interface facesetMapper {
    void addfaceset(String name, String uploadTime, String details, String master, String appkey, Object faceStoreId);
    faceset getAppKey(Object faceStoreId);
    List<faceset> queryFaceSets(String master);
    void updateCount(String master,String faceStoreId,int count);
    void delFaceSet(String master,String faceStoreId);

}
