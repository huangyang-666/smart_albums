package com.huang.pojo.typesModule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author huangyang
 * @date 2021年06月16日 16:26
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class other {
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
