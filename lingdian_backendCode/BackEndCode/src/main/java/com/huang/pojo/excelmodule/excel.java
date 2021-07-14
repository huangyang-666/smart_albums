package com.huang.pojo.excelmodule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author huangyang
 * @date 2021年06月20日 11:10
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class excel {
    private int id;
    private String name;
    private String type;
    private String faceStoreId;
    private String faceId;

}
