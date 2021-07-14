package com.huang.modelDao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description 图片上传并识别部分的表单信息dao
 * @Author huangyang
 * @date 2021年06月03日 15:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class picUploadModel {
    private MultipartFile[] files;
    private String username;
    private String password;
    private String details;
}
