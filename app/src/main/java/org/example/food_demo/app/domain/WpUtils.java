package org.example.food_demo.app.domain;

import com.alibaba.fastjson.JSON;

import java.util.Base64;

public class WpUtils {
    public static <T> T fromBase64Json(String base64Json, Class<T> clazz) {
        if (base64Json == null || base64Json.trim().isEmpty()) {
            return null;
        }
        String json = new String(Base64.getDecoder().decode(base64Json));
        return JSON.parseObject(json, clazz);
    }


}