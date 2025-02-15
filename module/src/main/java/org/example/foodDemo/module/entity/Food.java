package org.example.foodDemo.module.entity;


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
    private int viewCount;
    private int createTime;
    private int updateTime;
    private int isDeleted;
}



