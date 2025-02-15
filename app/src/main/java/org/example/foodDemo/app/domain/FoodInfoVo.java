package org.example.foodDemo.app.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.util.List;

@Data
@Accessors(chain = true)
public class FoodInfoVo {
    private String foodName;
    private String publishTime;
    private String foodIntroduce;
    private int pageView;
    private List slideShow;


}
