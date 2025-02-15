package org.example.foodDemo.app.domain;



import lombok.experimental.Accessors;

import java.util.List;


@Accessors(chain = true)
public class FoodListVo {

    private List<FoodInfoVo> list;
    public FoodListVo(List<FoodInfoVo> list) {
        this.list = list;
    }

    public List<FoodInfoVo> getList() {
        return list;
    }

    public void setList(List<FoodInfoVo> list) {
        this.list = list;
    }
}