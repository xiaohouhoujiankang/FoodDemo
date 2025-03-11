package org.example.food_demo.app.domain;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CategoryItemVo {
    private BigInteger categoryId;
    private String categoryName;
    private String categoryImage;
}
