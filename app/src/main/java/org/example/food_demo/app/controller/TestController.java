package org.example.food_demo.app.controller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class TestController {
    @RequestMapping("/test/stringbuilder")
    public Integer testStringBuilder() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(1);
        }
        StringBuilder sb = new StringBuilder();
        for (Integer i : list) {
            new Thread(() -> {
                sb.append(i);
            }).start();
        }
        log.info(String.valueOf((sb.toString().length())));
        return sb.toString().length();

    }
    @RequestMapping("/test/stringbuffer")
    public Integer testStringBuffer(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.add(1);
        }
        StringBuffer sb = new StringBuffer();
        for (Integer i : list) {
            new Thread(() -> {
                sb.append(i);
            }).start();
        }

       log.info(String.valueOf(sb.toString().length()));
        return sb.toString().length();
    }
}
