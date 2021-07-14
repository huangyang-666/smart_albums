package com.huang.pojo.faceModule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author huangyang
 * @date 2021年06月18日 14:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class result {
    private String filename;
    private String type;
    private String fileSize;
    private String master;
    private String date;
    private String faceSetId;
    private String faceId;
    private String originName;

}
