package org.example.food_demo.console.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.List;

@Data
public class FoodInfoVo {
    private String foodName;
    private String publishTime;
    private String foodIntroduce;
    private Integer pageView;
    private List<String> slideShow;
    private String createTime;
    private String updateTime;

}
