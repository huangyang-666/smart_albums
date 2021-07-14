package com.huang.mapper;

import com.huang.pojo.pathInfo.storepath;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @Description “云端地址”模块的操作mapper
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */

@Mapper
@Repository
//各个大类的存储地址
public interface storepathmapper {
//    查询所有类的存储地址
    List<storepath> querystorepaths();
//  查询某个类的地址
    storepath queryPathByType(String imgType);

}
