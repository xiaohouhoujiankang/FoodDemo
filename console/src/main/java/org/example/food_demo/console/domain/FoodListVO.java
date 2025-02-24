package org.example.food_demo.console.domain;

import lombok.Data;

import java.util.List;
@Data
public class FoodListVO {
    private List<FoodItemVo> list;
    private Integer total;
    private Integer pageSize;
    public FoodListVO(List<FoodItemVo> list, Integer total, Integer pageSize) {
        this.list = list;
        this.total = total;
        this.pageSize = pageSize;
    }
}
