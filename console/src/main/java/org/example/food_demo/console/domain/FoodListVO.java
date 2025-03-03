package org.example.food_demo.console.domain;

import lombok.Data;

import java.util.List;
@Data
public class FoodListVO {
    private List<FoodItemVo> list;
    private Long total;
    private Integer pageSize;
    public FoodListVO(List<FoodItemVo> list, Long total, Integer pageSize) {
        this.list = list;
        this.total = total;
        this.pageSize = pageSize;
    }
}
