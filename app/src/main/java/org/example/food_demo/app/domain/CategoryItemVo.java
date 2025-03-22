package org.example.food_demo.app.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryItemVo {
    private BigInteger categoryId;
    private String categoryName;
    private String categoryImage;
    private BigInteger parentId;
    private List<CategoryItemVo> subCategories;
}
