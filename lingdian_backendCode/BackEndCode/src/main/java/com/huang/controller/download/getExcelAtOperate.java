package com.huang.controller.download;

import com.huang.controller.faceDistingush.excelUtil.getExcel;
import com.huang.mapper.allimgmapper;
import com.huang.pojo.excelmodule.excel;
import com.huang.pojo.faceModule.result;
import com.huang.pojo.typesModule.commonimg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @Description 在照片操作页面获得Excel报表
 * @Author huangyang
 * @date 2021年06月22日 9:57
 */

@RestController
public class getExcelAtOperate {

    @Autowired
    com.huang.mapper.allimgmapper allimgmapper;

    @RequestMapping("/getExcelAtOperate")
    public Object getExcelAtOperate(@RequestParam("filenames")String filenames[],
                                    @RequestParam("user")String master,
                                    HttpServletResponse resp,
                                    HttpServletRequest req) throws IOException {
        //        获取当前照片的全部信息
        HashSet<result> comCome = new HashSet<>();
        for (String filename : filenames) {
            commonimg imgInfo = allimgmapper.queryImgInfoByName(filename,master);
//              newface.add(new result(master+format1+file.getOriginalFilename(), "human", file.getSize() + "", master, sdf.format(new Date()),
//                            "", "",file.getOriginalFilename()));
            comCome.add(new result("",imgInfo.getImgType(),imgInfo.getImgSize()+"",master,"",
                    imgInfo.getFaceSet(),imgInfo.getFaceId(),imgInfo.getImgName()));
        }
        //        生成excel报表
        String[] title={"id","filename","type","faceSetId","faceId"};
        int flag=1;
        ArrayList<excel> list = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        //        common结果
        for (result result : comCome) {
            excel temp = new excel(flag++,result.getOriginName(),result.getType(),result.getFaceSetId(),result.getFaceId());
            list.add(temp);
        }
        for (int i = 0; i < list.size(); i++) {
            ArrayList<String> infos=new ArrayList<>();
            infos.add(list.get(i).getId()+"");
            infos.add(list.get(i).getName()+"");
            infos.add(list.get(i).getType()+"");
            infos.add(list.get(i).getFaceStoreId()+"");
            infos.add(list.get(i).getFaceId()+"");
            map.put(list.get(i).getId()+"",infos);
        }
//        利用工具类将照片信息全部注入excel表格中
        getExcel.createExcel(master,map,title);
//        将excel下载请求发送到客户端
        //            获取下载文件的名字
        String filename=master+"outcome.xls";
        String filepath="D:\\1rjb\\webphoto2\\static\\smart_photos\\download\\"+master+"outcome.xls";
        //            设置浏览器支持的响应头
        resp.setHeader("Content-Disposition", "attachment;filename=" + filename);
        //            获取下载文件的输入流
        File targetFile = new File(filepath);
        FileInputStream in = new FileInputStream(targetFile.getPath());
        //            创建缓冲区
        int len = 0;
        byte[] buffer = new byte[1024];
//            获取OutPutStream输出对象
        ServletOutputStream out = resp.getOutputStream();
//            将FileOutputStream流写入到buffer缓冲区，使用OutPutStream将缓冲区中的数据输出到客户端
        while ((len = in.read(buffer)) > 0) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();


        return "success";
    }


}
