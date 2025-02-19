package org.example.food_demo.app.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class FoodItemVo {
    private BigInteger foodId;
    private String foodPhoto;
    private String foodName;
}
