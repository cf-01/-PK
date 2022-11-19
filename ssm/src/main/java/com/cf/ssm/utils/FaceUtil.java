package com.cf.ssm.utils;

import com.baidu.aip.face.AipFace;
import org.json.JSONObject;

import java.util.HashMap;

public class FaceUtil {
    //设置APPID/AK/SK
    public static final String APP_ID = "27984404";
    public static final String API_KEY = "q5pGF3ZEbL1WZIdRyEZ0pvxH";
    public static final String SECRET_KEY = "4bxulWPN58dLMjn51CNWE7QhSaQsvmyS";

    public static String getFaceScore(String image) {
        System.out.println(image);
        // 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 传入可选参数调用接口
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("face_field", "age,beauty,emotion,face_type,mask,eye_status,glasses,gender,face_shape,age,expression");
        options.put("max_face_num", "2");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "LOW");

        String imageType = "BASE64";
        // 人脸检测
        JSONObject res = client.detect(image, imageType, options);

        return res.toString(2);
    }
}