package com.huang.controller.autoVideoModule;

import ws.schild.jave.*;

import java.io.File;

/**
 * @Description 视频转码工具类
 * @Author huangyang
 * @date 2021年06月22日 16:02
 */

public class changeCode {

    public static void  changeCodeUtil(String sourcepath,String targetpath){
        File source = new File(sourcepath);
        File target = new File(targetpath);
//        AudioAttributes audio= new AudioAttributes();
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(new Integer(800000));
        audio.setChannels(new Integer(1));
        VideoAttributes video=new VideoAttributes();
        video.setCodec("libx264");
        video.setBitRate(new Integer(3200000));
        video.setFrameRate(new Integer(15));
        EncodingAttributes attr=new EncodingAttributes();
        attr.setFormat("mp4");
        attr.setAudioAttributes(audio);
        attr.setVideoAttributes(video);
        Encoder encoder=new Encoder();
        MultimediaObject multimediaObject = new MultimediaObject(source);
        try{
            encoder.encode(multimediaObject, target, attr);
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }catch (InputFormatException e){
            e.printStackTrace();

        }catch (EncoderException e){
            e.printStackTrace();
        }
    }


}
