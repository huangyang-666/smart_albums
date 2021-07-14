package com.huang.controller.autoVideoModule;

import com.huang.mapper.*;
import com.huang.mapper.moments.Momentsmapper;
import com.huang.pojo.faceModule.human;
import com.huang.pojo.typesModule.*;
import org.apache.http.entity.ContentType;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.FrameRecorder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 生成精彩时刻接口
 * @Author huangyang
 * @date 2021年06月22日 16:02
 */
@RestController
public class Fusion {

    @Autowired
    com.huang.mapper.animalmapper animalmapper;
    @Autowired
    com.huang.mapper.constructmapper constructmapper;
    @Autowired
    com.huang.mapper.vehiclemapper vehiclemapper;
    @Autowired
    allimgmapper allimgmapper;
    @Autowired
    com.huang.mapper.scenerymapper scenerymapper;
    @Autowired
    com.huang.mapper.faceMapper.humanmapper humanmapper;
    @Autowired
    othermapper othermapper;
    @Autowired
    Momentsmapper momentsmapper;


    @RequestMapping("/happymoments")
    public String hc(@RequestParam("user") String master,
                     @RequestParam("type") String type
//                      String audiopath,String outpath, String mp4SavePath,String imgPath
    ) throws Exception {
//        audio----视频地址   outpath       imgPath---图片存放路径   mp4SavePath---视频保存路径
//        1.根据传入的类别选出照片，放到合适的位置,选取最新的十张照片
//        根据当前用户生成一个用户专属文件夹
        System.out.println("精彩时刻"+master+type);
        File originPath = new File("D:\\1rjb\\webphoto2\\static\\videoTempStorePath\\" + master);
        if (!originPath.isDirectory()) {
            originPath.mkdirs();
        }
        File[] listFiles = originPath.listFiles();
        for (File listFile : listFiles) {
            listFile.delete();      //清空之前容器中的照片
        }
        if (type.equals("all")) {
            List<commonimg> imgs = allimgmapper.queryallimgs(master);
            if (imgs.size() < 10) {
                return "failed";
            } else {
                for (int i = imgs.size() - 1; i > imgs.size() - 10; i--) {
                    File file = new File(imgs.get(i).getImgPath());
                    File dest = new File(originPath + "\\" + imgs.get(i).getName());
//                    将file类型转换成multifile
                    FileInputStream inputStream = new FileInputStream(file);
                    MultipartFile mulfile = new MockMultipartFile(file.getName(), file.getName(),
                            ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
//                  实现照片的分发
                    mulfile.transferTo(dest);
                }
            }
        }
        if (type.equals("animal")) {
            List<animal> imgs = animalmapper.queryanimals(master);
            if (imgs.size() < 10) {
                return "failed";
            } else {
                for (int i = imgs.size() - 1; i > imgs.size() - 10; i--) {
                    File file = new File(imgs.get(i).getImgPath());
                    File dest = new File(originPath + "\\" + imgs.get(i).getName());
//                    将file类型转换成multifile
                    FileInputStream inputStream = new FileInputStream(file);
                    MultipartFile mulfile = new MockMultipartFile(file.getName(), file.getName(),
                            ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
//                  实现照片的分发
                    mulfile.transferTo(dest);
                }
            }
        }
        if (type.equals("human")) {
            List<human> imgs = humanmapper.queryhumans(master);
            if (imgs.size() < 10) {
                return "failed";
            } else {
                for (int i = imgs.size() - 1; i > imgs.size() - 10; i--) {
                    File file = new File(imgs.get(i).getImgPath());
                    File dest = new File(originPath + "\\" + imgs.get(i).getName());
//                    将file类型转换成multifile
                    FileInputStream inputStream = new FileInputStream(file);
                    MultipartFile mulfile = new MockMultipartFile(file.getName(), file.getName(),
                            ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
//                  实现照片的分发
                    mulfile.transferTo(dest);
                }
            }
        }
        if (type.equals("construct")) {
            List<construct> imgs = constructmapper.queryconstructs(master);
            if (imgs.size() < 10) {
                return "failed";
            } else {
                for (int i = imgs.size() - 1; i > imgs.size() - 10; i--) {
                    File file = new File(imgs.get(i).getImgPath());
                    File dest = new File(originPath + "\\" + imgs.get(i).getName());
//                    将file类型转换成multifile
                    FileInputStream inputStream = new FileInputStream(file);
                    MultipartFile mulfile = new MockMultipartFile(file.getName(), file.getName(),
                            ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
//                  实现照片的分发
                    mulfile.transferTo(dest);
                }
            }
        }
        if (type.equals("scenery")) {
            List<scenery> imgs = scenerymapper.querysceneries(master);
            if (imgs.size() < 10) {
                return "failed";
            } else {
                for (int i = imgs.size() - 1; i > imgs.size() - 10; i--) {
                    File file = new File(imgs.get(i).getImgPath());
                    File dest = new File(originPath + "\\" + imgs.get(i).getName());
//                    将file类型转换成multifile
                    FileInputStream inputStream = new FileInputStream(file);
                    MultipartFile mulfile = new MockMultipartFile(file.getName(), file.getName(),
                            ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
//                  实现照片的分发
                    mulfile.transferTo(dest);
                }
            }
        }
        if (type.equals("vehicle")) {
            List<vehicle> imgs = vehiclemapper.queryvehicles(master);
            if (imgs.size() < 10) {
                return "failed";
            } else {
                for (int i = imgs.size() - 1; i > imgs.size() - 10; i--) {
                    File file = new File(imgs.get(i).getImgPath());
                    File dest = new File(originPath + "\\" + imgs.get(i).getName());
//                    将file类型转换成multifile
                    FileInputStream inputStream = new FileInputStream(file);
                    MultipartFile mulfile = new MockMultipartFile(file.getName(), file.getName(),
                            ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
//                  实现照片的分发
                    mulfile.transferTo(dest);
                }
            }
        }
        if (type.equals("other")) {
            List<other> imgs = othermapper.queryothers(master);
            if (imgs.size() < 10) {
                return "failed";
            } else {
                for (int i = imgs.size() - 1; i > imgs.size() - 10; i--) {
                    File file = new File(imgs.get(i).getImgPath());
                    File dest = new File(originPath + "\\" + imgs.get(i).getName());
//                    将file类型转换成multifile
                    FileInputStream inputStream = new FileInputStream(file);
                    MultipartFile mulfile = new MockMultipartFile(file.getName(), file.getName(),
                            ContentType.APPLICATION_OCTET_STREAM.toString(), inputStream);
//                  实现照片的分发
                    mulfile.transferTo(dest);
                }
            }
        }
        System.out.println("文件数量" + listFiles.length);
//        至此,照片已全部分发到目录---->File originPath = new File("D:\\1rjb\\webphoto2\\static\\videoTempStorePath\\" + master+"\\"+"imgs");

        int width = 1600;//视频长像素个数
        int height = 900; // 视频宽像素个数
        //读取所有图片
        File[] files = originPath.listFiles();
        Map<Integer, File> imgMap = new HashMap<Integer, File>();
        int num = 0;
        for (File imgFile : files) {
            imgMap.put(num++, imgFile);
        }
//        无音视频保存地址
        File filePath = new File("D:\\1rjb\\webphoto2\\static\\video\\" + "originVideo");
        if (!filePath.isDirectory()) {
            filePath.mkdirs();
        }
        File filePath2 = new File("D:\\1rjb\\webphoto2\\static\\video\\" + "tempVideo");
        if (!filePath2.isDirectory()) {
            filePath2.mkdirs();
        }
        File filePath3 = new File("D:\\1rjb\\webphoto2\\static\\video\\" + "finalvideo");
        if (!filePath3.isDirectory()) {
            filePath3.mkdirs();
        }
        String mp4SavePath = "D:\\1rjb\\webphoto2\\static\\video\\" + "originVideo\\" + "happymoments.mp4";
        JianJi.createMp4(mp4SavePath, imgMap, width, height);

        String outpath = "D:\\1rjb\\webphoto2\\static\\video\\" +"tempVideo\\" + "happymoments.mp4";
//        根据类别选择音频
        String audiopath = "";
        if (type.equals("animal")) audiopath="D:\\1rjb\\webphoto2\\static\\voicePath\\animal.mp3";
        if (type.equals("all")) audiopath="D:\\1rjb\\webphoto2\\static\\voicePath\\all.mp3";
        if (type.equals("vehicle")) audiopath="D:\\1rjb\\webphoto2\\static\\voicePath\\vehicle.mp3";
        if (type.equals("human")) audiopath="D:\\1rjb\\webphoto2\\static\\voicePath\\human.mp3";
        if (type.equals("construct")) audiopath="D:\\1rjb\\webphoto2\\static\\voicePath\\construct.mp3";
        if (type.equals("scenery")) audiopath="D:\\1rjb\\webphoto2\\static\\voicePath\\scenery.mp3";

        mergeAudioAndVideo(mp4SavePath, audiopath, outpath);      //合成视频(视频+音频)---->生成需要的视频
//        zm(outpath,target);  转码
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String target = "D:\\1rjb\\webphoto2\\static\\video\\" + "finalvideo\\" +master+ sdf.format(date) + "happymoments.mp4";         //转码之后视频的最终地址
        changeCode.changeCodeUtil(outpath, target);          //转码
//        编写数据库增加一个精彩时刻
        momentsmapper.addmoment(master+sdf.format(date) + "happymoments.mp4", master,
                "D:\\1rjb\\webphoto2\\static\\video\\finalvideo\\" + master+sdf.format(date) + "happymoments.mp4", sdf2.format(date), type);
        return "success";

    }

    public static boolean mergeAudioAndVideo(String videoPath, String audioPath, String outPut) throws Exception {
        boolean isCreated = true;
        File file = new File(videoPath);
        if (!file.exists()) {
            return false;
        }
        FrameRecorder recorder = null;
        FrameGrabber grabber1 = null;
        FrameGrabber grabber2 = null;
        try {
            //抓取视频帧
            grabber1 = new FFmpegFrameGrabber(videoPath);
            //抓取音频帧
            grabber2 = new FFmpegFrameGrabber(audioPath);
            grabber1.start();
            grabber2.start();
            //创建录制
            recorder = new FFmpegFrameRecorder(outPut,
                    grabber1.getImageWidth(), grabber1.getImageHeight(),
                    grabber2.getAudioChannels());

            recorder.setFormat("mp4");
            recorder.setFrameRate(grabber1.getFrameRate());
            recorder.setSampleRate(grabber2.getSampleRate());
            recorder.start();

            org.bytedeco.javacv.Frame frame1;
            Frame frame2;
            //先录入视频
            while ((frame1 = grabber1.grabFrame()) != null) {
                recorder.record(frame1);
            }
            //然后录入音频
            while ((frame2 = grabber2.grabFrame()) != null) {
                recorder.record(frame2);
            }
            grabber1.stop();
            grabber2.stop();
            recorder.stop();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (recorder != null) {
                    recorder.release();
                }
                if (grabber1 != null) {
                    grabber1.release();
                }
                if (grabber2 != null) {
                    grabber2.release();
                }
            } catch (FrameRecorder.Exception e) {
                e.printStackTrace();
            }
        }
        return isCreated;

    }
}
