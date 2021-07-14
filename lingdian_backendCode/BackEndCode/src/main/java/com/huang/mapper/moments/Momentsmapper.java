package com.huang.mapper.moments;

import com.huang.pojo.moments.happymoment;
import org.apache.ibatis.annotations.Mapper;
import org.bytedeco.opencv.opencv_core.Moments;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @Author huangyang
 * @date 2021年06月25日 23:41
 */
@Mapper
@Repository
public interface Momentsmapper {
    void addmoment(String name, String master, String storePath, String uploadTime, String videoType);

    List<happymoment> querymoments(String master, String type);

}
