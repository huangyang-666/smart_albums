package com.huang.pojo.moments;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author huangyang
 * @date 2021年06月25日 14:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class happymoment {
    private int id;
    private String name;
    private String master;
    private String storePath;
    private String uploadTime;
    private String videoType;


}
