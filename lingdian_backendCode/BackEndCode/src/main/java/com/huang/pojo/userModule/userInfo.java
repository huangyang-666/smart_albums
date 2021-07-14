package com.huang.pojo.userModule;

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
public class userInfo {
    private int id;
    private String username;
    private String password;
    private int authority;
}
