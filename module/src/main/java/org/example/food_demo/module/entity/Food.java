package org.example.food_demo.module.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;

@Data
@Accessors(chain = true)
@TableName("food")
public class Food {
    private BigInteger id;
    private String name;
    private String foodPhotos;
    private String foodIntroduce;
    private Integer viewCount;
    private String createTime;
    private String updateTime;
    private Integer isDeleted;
}



