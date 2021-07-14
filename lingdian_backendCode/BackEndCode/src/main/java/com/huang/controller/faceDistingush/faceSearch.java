package com.huang.controller.faceDistingush;

import com.chinamobile.bcop.api.sdk.ai.core.exception.ClientException;
import com.chinamobile.bcop.api.sdk.ai.core.model.CommonJsonObjectResponse;
import com.chinamobile.bcop.api.sdk.ai.facebody.AiFaceBody;
import com.huang.mapper.allimgmapper;
import com.huang.mapper.faceMapper.facesetMapper;
import com.huang.mapper.faceMapper.humanmapper;
import com.huang.pojo.faceModule.faceset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Description 正已有的人脸库中搜索是否存在图片中的人脸，如果有匹配项就将照片加入到人脸库中,没有匹配项的返回下标给前端
 * @Author huangyang
 * @date 2021年06月14日 11:49
 */

@RestController
public class faceSearch {
    @Autowired
    facesetMapper facesetMapper;
    @Autowired
    com.huang.mapper.faceMapper.humanmapper humanmapper;
    @Autowired
    com.huang.mapper.allimgmapper allimgmapper;

    @RequestMapping("/facesearch")
    public Object facesearch(@RequestParam("facefiles") MultipartFile[] files,
                             @RequestParam("user") String master) throws ClientException, IOException {
        HashMap<String, int[]> newfaces = new HashMap<>();
        List<faceset> facesets = facesetMapper.queryFaceSets(master);
        AiFaceBody instance = FaceAi.getInstance();
        StringBuffer newinfo = new StringBuffer("");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < files.length; i++) {
            for (faceset faceset : facesets) {
//                进行人脸搜索
                CommonJsonObjectResponse response = instance.faceSearch("D:\\1rjb\\webphoto2\\static\\smart_photos\\allimgs\\" + files[i].getOriginalFilename(),
                        Integer.valueOf(faceset.getFaceStoreId().replace(".0", "")) + "", 1, faceset.getAppkey() + "", null);
                String results = response.getCommonResult().get("results").toString();
                double confidence= Double.parseDouble(results.substring(results.lastIndexOf("=")+1,results.length()-2));
                System.out.println(confidence);
                if (response.getCommonResult() != null && confidence>=0.72 ) {    //置信度划分设置为0.7(置信度匹配史低为0.7)
                    System.out.println("匹配上了===>" + faceset.getFaceStoreId() + "==>" + response.getCommonResult());
//                将能匹配的照片上传到人脸库中
                    CommonJsonObjectResponse faceToFile = instance.createFaceToFile(Integer.valueOf(faceset.getFaceStoreId().replace(".0", "")), files[i].getBytes(),
                            faceset.getName(), "unsure", faceset.getAppkey(), null);
                    Object faceId = faceToFile.getCommonResult().get("faceId");
//                    更新人脸类数据库
                    humanmapper.addhumanInfo(files[i].getOriginalFilename(), sdf.format(new Date()), files[i].getSize(), "human",
                            "unsure", "D:\\1rjb\\webphoto2\\static\\smart_photos\\humans\\" + files[i].getOriginalFilename(),
                            master, "123456", faceset.getName(), faceset.getFaceStoreId(),faceId.toString());
//                    更新所有图片的数据库 更新faceId设置
//                    allimgmapper.updateFaceId(master,faceset.getFaceStoreId(),files[i].getOriginalFilename(),faceId.toString());

//                    更新人脸库数据库
                    System.out.println(faceset.getImgcount());
                    facesetMapper.updateCount(master,faceset.getFaceStoreId(), faceset.getImgcount() + 1);
                    break;
                } else {
                    //记录下没有匹配成功的人脸照片,这是一张新面孔
                    if (facesets.get(facesets.size() - 1) == faceset) {
                        newinfo.append(i + "/");
                    }
                }
            }
        }
//        以下这些照片为新面孔
        String[] newres = newinfo.toString().split("/");
        int[] newface = null;
        if (!newinfo.toString().equals("")) {
            newface = new int[newres.length];
            for (int i = 0; i < newface.length; i++) {
                newface[i] = Integer.valueOf(newres[i]);
                System.out.println("新面孔有图片文件序号" + newface[i]);
            }
        }
        newfaces.put("newfaces", newface);
        return newfaces;
    }


}
