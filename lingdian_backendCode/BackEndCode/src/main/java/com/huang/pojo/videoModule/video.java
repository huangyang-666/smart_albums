package com.huang.pojo.videoModule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @Description 用户信息实体类
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class video {
    private int id;
    private String uploadTime;
    private String videoName;
    private String videoPath;
    private String master;

}
