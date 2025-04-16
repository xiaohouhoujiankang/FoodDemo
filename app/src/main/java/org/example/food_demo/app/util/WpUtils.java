package org.example.food_demo.app.util;

import com.alibaba.fastjson.JSON;
import com.mysql.cj.util.Base64Decoder;

import java.util.Base64;

public class WpUtils {
    public static <T> T fromBase64Json(String base64Json, Class<T> clazz) {
        if (base64Json == null || base64Json.trim().isEmpty()) {
            return null;
        }
        byte[] decodedBytes = Base64.getDecoder().decode(base64Json);
        String json = new String(decodedBytes);
        return JSON.parseObject(json, clazz);
    }


}