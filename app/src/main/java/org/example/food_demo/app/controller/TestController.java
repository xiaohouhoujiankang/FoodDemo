package org.example.food_demo.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @RequestMapping("/test/stringbuilder")
    public String testStringBuilder() {
        int startTime = (int) System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(1);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i));
        }
        int endTime = (int) System.currentTimeMillis();
        System.out.println("使用 StringBuilder 拼接字符串耗时为:" + (endTime - startTime) + "ms");
        return "StringBuilder 拼接耗时：" + (endTime - startTime) + "ms，字符串长度为：" + stringBuilder.length();
    }
    @RequestMapping("/test/stringbuffer")
    public String testStringBuffer(){
        int startTime = (int) System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(1);
        }
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0;i < list.size();i++){
            stringBuffer.append(list.get(i));
        }
        int endTime = (int) System.currentTimeMillis();
        System.out.println("使用 StringBuffer 拼接字符串耗时为:" + (endTime - startTime) + "ms");
        return "StringBuffer 拼接耗时：" + (endTime - startTime) + "ms，字符串长度为：" + stringBuffer.length();
    }
}
