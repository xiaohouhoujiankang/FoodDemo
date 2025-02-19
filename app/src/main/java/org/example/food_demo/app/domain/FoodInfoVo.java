package org.example.food_demo.app.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class FoodInfoVo {
    private String foodName;
    private String publishTime;
    private String foodIntroduce;
    private Integer pageView;
    private List<String> slideShow;

}
