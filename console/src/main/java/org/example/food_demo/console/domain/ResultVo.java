package org.example.food_demo.console.domain;


import lombok.Data;

import java.math.BigInteger;

@Data
public class ResultVo {
    private String message;
    private BigInteger foodId;

    public ResultVo(String message,BigInteger foodId) {
        this.message = message;
        this.foodId = foodId;

    }
}
