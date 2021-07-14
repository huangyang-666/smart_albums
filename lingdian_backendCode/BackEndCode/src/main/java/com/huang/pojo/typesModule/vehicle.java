package com.huang.pojo.typesModule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @Description 车辆实体类
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class vehicle {
    private int id;
    private String imgName;
    private String uploadTime;
    private double imgSize;
    private String imgType;
    private String details;
    private String imgPath;
    private String master;
    private String name;

}
