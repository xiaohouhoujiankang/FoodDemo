package org.example.food_demo.app.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;


import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class WpUtils {
    public static <T> T fromBase64Json(String base64Json, Class<T> clazz) {
        if (base64Json == null || base64Json.trim().isEmpty()) {
            return null;
        }
        base64Json = base64Json.replaceAll("\\s", "");

        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64Json);
            String json = new String(decodedBytes, StandardCharsets.UTF_8);
            System.out.println("Decoded JSON: " + json);
            return JSON.parseObject(json, clazz);
        } catch (IllegalArgumentException | JSONException e) {
            System.out.println("Error decoding or parsing JSON: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static <T> String toBase64Json(T object) {
        if (object == null) {
            return null;
        }
        try {
            String jsonStr = JSON.toJSONString(object);
            byte[] jsonBytes = jsonStr.getBytes(StandardCharsets.UTF_8);
            byte[] encodedBytes = Base64.getEncoder().encode(jsonBytes);
            return new String(encodedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println("Error encoding to Base64: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}