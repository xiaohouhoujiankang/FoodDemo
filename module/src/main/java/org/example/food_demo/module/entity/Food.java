package org.example.food_demo.module.entity;


import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Data
@Accessors(chain = true)
public class Food {
    private BigInteger id;
    private String name;
    private String foodPhotos;
    private String foodIntroduce;
    private Integer viewCount;
    private Integer createTime;
    private Integer updateTime;
    private Integer isDeleted;
}



