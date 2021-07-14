package com.huang.pojo.faceModule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author huangyang
 * @date 2021年06月13日 18:02
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class faceset {
    private int id;
    private String faceStoreId;
    private String name;
    private String uploadTime;
    private String details;
    private String master;
    private String appkey;
    private int imgcount;

}
