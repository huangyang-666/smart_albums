package com.huang.pojo.showtimeModule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * @Description “发现时刻”实体类
 * @Author huangyang
 * @date 2021年05月29日 14:13
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
//发现功能需要的实体类
public class showtime {
    private int id;
    private String imgPath;
    private String name;
    private String master;
    private String mood;
    private String date;


}
