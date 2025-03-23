package org.example.food_demo.app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class TestController {
    @RequestMapping("/test/stringbuilder")
    public String testStringBuilder() {
        int startTime = (int) System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(1);
        }
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        StringBuilder[] stringBuilders = new StringBuilder[10];
        for (int i = 0; i < 10; i++) {
            stringBuilders[i] = new StringBuilder();
        }

        int chunkSize = list.size() / 10;
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.submit(() -> {
                int start = finalI * chunkSize;
                int end = (finalI == 9) ? list.size() : (finalI + 1) * chunkSize;
                for (int j = start; j < end; j++) {
                    stringBuilders[finalI].append(list.get(j));
                }
            });
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {

        }

        StringBuilder finalStringBuilder = new StringBuilder();
        for (int i = 0; i < stringBuilders.length; i++) {
            finalStringBuilder.append(stringBuilders[i]);
        }

        int endTime = (int) System.currentTimeMillis();
        System.out.println("使用 StringBuilder 拼接字符串耗时为:" + (endTime - startTime) + "ms");
        return "StringBuilder 拼接耗时：" + (endTime - startTime) + "ms，字符串长度为：" + finalStringBuilder.length();
    }
    @RequestMapping("/test/stringbuffer")
    public String testStringBuffer(){
        int startTime = (int) System.currentTimeMillis();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(1);
        }
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        StringBuffer[] stringBuffers = new StringBuffer[10];
        for (int i = 0; i < 10; i++) {
            stringBuffers[i] = new StringBuffer();
        }
        int chunkSize = list.size() / 10;
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executorService.submit(() -> {
                int start = finalI * chunkSize;
                int end = (finalI == 9) ? list.size() : (finalI + 1) * chunkSize;
                for (int j = start; j < end; j++) {
                    stringBuffers[finalI].append(list.get(j));
                }
            });
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
        StringBuffer finalStringBuffer = new StringBuffer();
        for (int i = 0; i < stringBuffers.length; i++) {
            finalStringBuffer.append(stringBuffers[i]);
        }
        int endTime = (int) System.currentTimeMillis();
        System.out.println("使用 StringBuffer 拼接字符串耗时为:" + (endTime - startTime) + "ms");
        return "StringBuffer 拼接耗时：" + (endTime - startTime) + "ms，字符串长度为：" + finalStringBuffer.length();
    }
}
