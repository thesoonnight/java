package com.bit702.suspendpad;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

import java.io.File;
import java.io.IOException;

/**
 * Tess4J测试类
 */
public class OcrDemo {

    public static String get(File file){
//        System.setProperty("TESSDATA_PREFIX", "<path_to_tessdata_parent_directory>");


//        String path = "E:/learnJava/OCR";		//我的项目存放路径
        String path = System.getProperty("user.dir");
        System.setProperty("TESSDATA_PREFIX", path );


        //String fileName = path + "\\Quicker_20221217_110816.png";
        //File file = new File(fileName);
        ITesseract instance = new Tesseract();

        /**
         *  获取项目根路径，例如： D:\IDEAWorkSpace\tess4J
         */
        File directory = new File(path);
        String courseFile = null;
        try {
            courseFile = directory.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //设置训练库的位置
        instance.setDatapath(path + "\\tessdata");

        instance.setLanguage("eng");//chi_sim ：简体中文， eng	根据需求选择语言库
        String result = null;
        try {
            long startTime = System.currentTimeMillis();
            result =  instance.doOCR(file);
            long endTime = System.currentTimeMillis();
            //System.out.println("Time is：" + (endTime - startTime) + " 毫秒");
        } catch (TesseractException e) {
            e.printStackTrace();
        }

        //System.out.println("result: ");
        //System.out.println(result);
        return result;
    }

}