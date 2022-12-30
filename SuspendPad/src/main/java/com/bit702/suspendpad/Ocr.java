package com.bit702.suspendpad;

import com.bit702.suspendpad.baidu.Base64Util;
import com.bit702.suspendpad.baidu.FileUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.sql.*;
public class Ocr {
    public static ArrayList<String> ocr(Image image) throws IOException {
        String jsonResult = JsonResult.getJsonResult(image);
        return OCRResultUtil.getOCRResult(jsonResult);
    }
}


class OCRResultUtil {
    private static final Gson gson = new Gson();

    /**
     * 解析百度OCR识别返回的json字符串为所有的结果
     *
     * @param jsonResult 百度OCR识别返回的json字符串
     * @return 解析完成的所有的识别的结果
     */
    public static ArrayList<String> getOCRResult(String jsonResult) {
        OCRResult ocrResult;
        ocrResult = gson.fromJson(jsonResult, OCRResult.class);

        ArrayList<String> result = new ArrayList<>(ocrResult.words_result_num);
        for (Words words : ocrResult.words_result) {
            result.add(words.words);
        }
        return result;
    }
}

class OCRResult {
    String log_id;
    int words_result_num;
    ArrayList<Words> words_result;

    @Override
    public String toString() {
        return "OCRResult{" +
                "log_id='" + log_id + '\'' +
                ", words_result_num=" + words_result_num +
                ", words_result=" + words_result +
                '}';
    }
}

class Words {
    String words;

    @Override
    public String toString() {
        return "Words{" +
                "words='" + words + '\'' +
                '}';
    }
}


class JsonResult {
    public static String getJsonResult(Image image) throws IOException {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";

        // 将 fxImage 类型转换为 BufferedImage 类型
        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image,
                new BufferedImage((int) image.getWidth(), (int) image.getHeight(), BufferedImage.TYPE_INT_RGB));
//        // 创建文件
//        File ocrFile = new File("ocrFile.jpg");
//        // 将 BufferedImage 写入文件
//        ImageIO.write(bufferedImage, "jpg", ocrFile);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = byteArrayOutputStream.toByteArray();
        try {
            byte[] imgData = bytes;
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");

            String param = "image=" + imgParam;

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = "24.a6537a0f2153845b2ec86d26896b93f1.2592000.1674977670.282335-29400727";

            String result = com.bit702.suspendpad.baidu.HttpUtil.post(url, accessToken, param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
