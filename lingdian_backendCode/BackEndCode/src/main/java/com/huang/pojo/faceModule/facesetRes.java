package com.huang.pojo.faceModule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author huangyang
 * @date 2021年06月26日 17:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class facesetRes {
    private int facesetid;
    private String faceSetName;
    private String details;
    private String date;
}
