package org.example.foodDemo.app.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Data
@Accessors(chain = true)
public class FoodInfoVo {
    private BigInteger id;
    private String name;
    private String publishTime;
    private String foodIntroduce;
    private int pageView;
    private String[] slideShow;
    private String foodPhotos;

}
