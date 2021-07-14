package com.huang.pojo.userModule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @Author huangyang
 * @date 2021年06月10日 2:22
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userregister {
    private String username;
    private String password;
    private String pwdtwo;

}
