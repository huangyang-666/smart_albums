package com.huang.controller.faceDistingush;

import com.chinamobile.bcop.api.sdk.ai.core.exception.ClientException;
import com.chinamobile.bcop.api.sdk.ai.core.model.CommonJsonObjectResponse;
import com.chinamobile.bcop.api.sdk.ai.facebody.AiFaceBody;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.huang.controller.commonDistingush.commonShibie;
import com.huang.controller.faceDistingush.excelUtil.getExcel;
import com.huang.mapper.*;
import com.huang.mapper.faceMapper.facesetMapper;
import com.huang.mapper.faceMapper.humanmapper;
import com.huang.pojo.excelmodule.excel;
import com.huang.pojo.faceModule.faceset;
import com.huang.pojo.faceModule.result;
import com.huang.pojo.smartShibie.smartRes;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description 人脸检测，将照片全部上传到allimgs文件夹中并检测上传的图片中是否存在人脸===>将照片分为两类（存在人脸和不存在人脸，并返回到前端），完成对照片的分流工作
 * @Author huangyang
 * @date 2021年06月14日 8:47
 */
@RestController
public class faceDetect {

    @Autowired
    com.huang.mapper.allimgmapper allimgmapper;
    @Autowired
    com.huang.mapper.faceMapper.facesetMapper facesetMapper;
    @Autowired
    com.huang.mapper.animalmapper animalmapper;
    @Autowired
    com.huang.mapper.constructmapper constructmapper;
    @Autowired
    com.huang.mapper.vehiclemapper vehiclemapper;
    @Autowired
    com.huang.mapper.scenerymapper scenerymapper;
    @Autowired
    othermapper othermapper;
    @Autowired
    com.huang.mapper.faceMapper.humanmapper humanmapper;


    //对于重复的照片，后端系统会自动覆盖重复的照片
//    人脸检测模块，负责检测上传的图片文件中是否存在人脸
    @RequestMapping("/facedetect")
    public List<smartRes> faceDetect(@RequestParam("user") String master,
                                     @RequestParam("files")MultipartFile[] files)
            throws IOException, ClientException {
        System.out.println(master);
//        File filepack = new File("D:\\1rjb\\webphoto2\\static\\fileTempStorePath\\" + master); //存放图片文件的文件夹
////        D:\1rjb\webphoto2\static\fileTempStorePath\admin
//        File[] files1 = filepack.listFiles();
//        MultipartFile[] files=new MultipartFile[files1.length];
//        System.out.println(files1.length);
////        file转MultiFile
//        int bj=0;
//        for (File file : files1) {
//            FileInputStream inputStream = new FileInputStream(file);
//            files[bj++]= new MockMultipartFile(file.getName(), file.getName(),
//            ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
//        }
        StringBuffer exist = new StringBuffer("");      //存放人脸照片序列
        StringBuffer unexist = new StringBuffer("");    //存放无人脸照片序列
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
        String format1 = sdf2.format(new Date());
        AiFaceBody instance = FaceAi.getInstance();
        for (int i = 0; i < files.length; i++) {
//            将照片上传到allimgs文件的文件夹中,并更新数据库
            String filename = files[i].getOriginalFilename().replace(",","");
            File dest = new File("D:\\1rjb\\webphoto2\\static\\smart_photos\\allimgs\\" + master + format1 + filename);
            try {
                files[i].transferTo(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            在allimgs文件夹中根据文件名遍历文件------->等会这个地方需要修改成allimgs的路径
            CommonJsonObjectResponse response = null;
            try {
                response = instance.faceDetect("D:\\1rjb\\webphoto2\\static\\smart_photos\\allimgs\\" + master + format1 + filename
                        , null);
            } catch (ClientException e) {
                e.printStackTrace();
            }
            System.out.println(response);
//          将ture|false序列单独存放
            if (response.getCommonResult() != null) {  //图片存在人脸.记录下序列
                exist.append(i + "/");
            } else {  //图片不存在人脸，记录下序列
                unexist.append(i + "/");
            }
        }
//        走人脸通道         极端判断--->为空判断
        MultipartFile[] faceval = null;       //初始为空
        if (!exist.toString().equals("")) {
            String[] split = exist.toString().split("/");
            faceval = new MultipartFile[split.length];
            for (int i = 0; i < split.length; i++) {
                faceval[i] = files[Integer.valueOf(split[i])];
                System.out.println("人脸可行集合--->序列为:" + Integer.valueOf(split[i]) + "的图片");
            }
        }

//        不走人脸通道        极端判断
        MultipartFile[] commonval = null;       //初始为空
        if (!unexist.toString().equals("")) {
            String[] split1 = unexist.toString().split("/");
            commonval = new MultipartFile[split1.length];
            for (int i = 0; i < split1.length; i++) {
                commonval[i] = files[Integer.valueOf(split1[i])];
                System.out.println("通用可行集合--->序列为:" + Integer.valueOf(split1[i]) + "的图片");
            }
        }

        List<smartRes> result=new ArrayList<>();

        HashSet<result> faceCome = new HashSet<>();
        HashSet<result> comCome = new HashSet<>();
        HashSet<result> newface = new HashSet<>();
//进行通用图像识别
        String filepath = "D:\\1rjb\\webphoto2\\static\\smart_photos\\";
        if (commonval != null) {
            Date date = new Date();
            String format = sdf.format(date);
            commonShibie commonShibie = new commonShibie();
            HashMap<Integer, String> comOutcome = commonShibie.commonShibie(commonval); //每个文件对应的大类
            System.out.println(comOutcome);
            //        重新匹配原始图片序列
            int flag = 0;
            for (MultipartFile file : commonval) {
                result.add(new smartRes(file.getOriginalFilename().replace(",",""), comOutcome.get(flag).substring(0, comOutcome.get(flag).indexOf("/")),master+format1+file.getOriginalFilename().replace(",",""),comOutcome.get(flag).substring(comOutcome.get(flag).indexOf("/")+1)));
                comCome.add(new result(master+format1+file.getOriginalFilename().replace(",",""), comOutcome.get(flag).substring(0, comOutcome.get(flag).indexOf("/")), file.getSize() + "", master, format,
                        null, null,file.getOriginalFilename().replace(",","")));
//                数据库信息更新&&文件上到指定的文件夹中
                allimgmapper.updateallimg(file.getOriginalFilename().replace(",",""), format, file.getSize(), comOutcome.get(flag).substring(0, comOutcome.get(flag).indexOf("/")), "unsure",
                        "D:\\1rjb\\webphoto2\\static\\smart_photos\\allimgs\\" + master + format1 + file.getOriginalFilename().replace(",",""), master, "123456",
                        master+format1+file.getOriginalFilename().replace(",",""), "");
                if (comOutcome.get(flag).substring(0, comOutcome.get(flag).indexOf("/")).equals("construct")) {
                    constructmapper.addconstructInfo(file.getOriginalFilename().replace(",",""), format, file.getSize(), "construct", "",
                            "D:\\1rjb\\webphoto2\\static\\smart_photos\\constructs\\" + master + format1 + file.getOriginalFilename().replace(",",""), master, "123456",
                            master+format1+file.getOriginalFilename().replace(",",""));
                    File dest = new File(filepath + "constructs\\" + master + format1 + file.getOriginalFilename().replace(",",""));
                    file.transferTo(dest);
                }
                if (comOutcome.get(flag).substring(0, comOutcome.get(flag).indexOf("/")).equals("scenery")) {
                    scenerymapper.addsceneryInfo(file.getOriginalFilename().replace(",",""), format, file.getSize(), "scenery", "",
                            "D:\\1rjb\\webphoto2\\static\\smart_photos\\sceneries\\" + master + format1 + file.getOriginalFilename().replace(",",""), master, "123456",
                            master+format1+file.getOriginalFilename().replace(",",""));
                    File dest = new File(filepath + "sceneries\\" + master + format1 + file.getOriginalFilename().replace(",",""));
                    file.transferTo(dest);
                }
                if (comOutcome.get(flag).substring(0, comOutcome.get(flag).indexOf("/")).equals("animal")) {
                    animalmapper.addanimalInfo(file.getOriginalFilename().replace(",",""), format, file.getSize(), "animal", "",
                            "D:\\1rjb\\webphoto2\\static\\smart_photos\\animals\\" + master + format1 + file.getOriginalFilename().replace(",",""), master, "123456",
                            master+format1+file.getOriginalFilename().replace(",",""));
                    File dest = new File(filepath + "animals\\" + master + format1 + file.getOriginalFilename().replace(",",""));
                    file.transferTo(dest);
                }
                if (comOutcome.get(flag).substring(0, comOutcome.get(flag).indexOf("/")).equals("vehicle")) {
                    vehiclemapper.addvehicleInfo(file.getOriginalFilename().replace(",",""), format, file.getSize(), "vehicle", "",
                            "D:\\1rjb\\webphoto2\\static\\smart_photos\\vehicles\\" + master + format1 + file.getOriginalFilename().replace(",",""), master, "123456",
                            master+format1+file.getOriginalFilename().replace(",",""));
                    File dest = new File(filepath + "vehicles\\" + master + format1 + file.getOriginalFilename().replace(",",""));
                    file.transferTo(dest);
                }
                if (comOutcome.get(flag).substring(0, comOutcome.get(flag).indexOf("/")).equals("other")) {
                    othermapper.addotherInfo(file.getOriginalFilename().replace(",",""), format, file.getSize(), "other", "",
                            "D:\\1rjb\\webphoto2\\static\\smart_photos\\others\\" + master + format1 + file.getOriginalFilename().replace(",",""), master, "123456",
                            master+format1+file.getOriginalFilename().replace(",",""));
                    File dest = new File(filepath + "others\\" + master + format1 + file.getOriginalFilename().replace(",",""));
                    file.transferTo(dest);
                }
                flag++;
            }
        }


//进行人脸识别、匹配
        //先选出这个用户有的人脸库
        if (faceval != null) {
            Date date = new Date();
            String format = sdf.format(date);
            List<faceset> facesets = facesetMapper.queryFaceSets(master);
            facesets.add(new faceset(-1,"119705","every","2021-6","eceryonehave","all","123456",0));
//        将人脸照片，传入人脸库中进行匹配
            faceSearchUtil faceSearchUtil = new faceSearchUtil();
//        获取人脸搜索的结果
            HashMap<Integer, String> faceOutcome = faceSearchUtil.facesearch(master,format1,faceval, facesets);
            System.out.println(faceOutcome);
            int flag = 0;
            for (MultipartFile file : faceval) {
//                能匹配上的人脸
                String s = faceOutcome.get(flag++);
                if (!s.equals("-1")) {
                    result.add(new smartRes(file.getOriginalFilename(), "human",master+format1+file.getOriginalFilename(),"已存在人脸库"));
                    faceCome.add(new result(master+format1+file.getOriginalFilename(), "human", file.getSize() + "", master, sdf.format(new Date()),
                            s.substring(0, s.indexOf("/")), s.substring(s.indexOf("/") + 1),file.getOriginalFilename()));
                    allimgmapper.updateallimg(file.getOriginalFilename().replace(",",""),format,file.getSize(),"human","",
                            "D:\\1rjb\\webphoto2\\static\\smart_photos\\allimgs\\" + master + format1 + file.getOriginalFilename().replace(",",""),
                            master,"123456",master + format1 + file.getOriginalFilename().replace(",",""),s.substring(s.indexOf("/") + 1));
                    humanmapper.addhumanInfo(file.getOriginalFilename().replace(",",""), format, file.getSize(), "human", "",
                            "D:\\1rjb\\webphoto2\\static\\smart_photos\\humans\\" + master + format1 + file.getOriginalFilename().replace(",",""),
                            master, "123456",master+format1+file.getOriginalFilename().replace(",",""), s.substring(0, s.indexOf("/")), s.substring(s.indexOf("/") + 1));
                    File dest = new File(filepath + "humans\\" + master + format1 + file.getOriginalFilename().replace(",",""));
                    file.transferTo(dest);
                } else {
//                新面孔
                    result.add(new smartRes(file.getOriginalFilename().replace(",",""), "stranger",master+format1+file.getOriginalFilename().replace(",",""),"不存在对应的人脸库"));
                    newface.add(new result(master+format1+file.getOriginalFilename().replace(",",""), "human", file.getSize() + "", master, sdf.format(new Date()),
                            "", "",file.getOriginalFilename().replace(",","")));
                }
            }
        }


//        汇总返回结果
        HashMap<String, HashSet<result>> outcome = new HashMap<>();
        outcome.put("common", comCome);     //通用的识别结果
        outcome.put("face", faceCome);       //人脸的识别结果
        outcome.put("newface", newface);

//        生成excel报表
        String[] title={"id","filename","type","faceSetId","faceId"};
        int flag=1;
        ArrayList<excel> list = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();
        //        common结果
        for (com.huang.pojo.faceModule.result result1 : comCome) {
            excel temp = new excel(flag++,result1.getOriginName(),result1.getType(),"NULL","NULL");
            list.add(temp);
        }
//        face结果
        flag=comCome.size()+1;
        for (com.huang.pojo.faceModule.result result1 : faceCome) {
            excel temp = new excel(flag++,result1.getOriginName(),result1.getType(),result1.getFaceSetId(),result1.getFaceId());
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
        getExcel.createExcel(master,map,title);

//        System.out.println("=================人脸检测模块结束===================");
        return result;
    }


}

