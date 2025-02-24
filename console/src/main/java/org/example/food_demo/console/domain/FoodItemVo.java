package org.example.food_demo.console.domain;

import lombok.Data;

import java.math.BigInteger;
@Data
public class FoodItemVo {
    private BigInteger foodId;
    private String foodPhoto;
    private String foodName;
}
