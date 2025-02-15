package org.example.foodDemo.app.domain;



import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigInteger;
import java.util.List;


@Accessors(chain = true)
public class FoodListVo {

    private List<FoodItemVo> list;
    public FoodListVo(List<FoodItemVo> list) {
        this.list = list;
    }

    public List<FoodItemVo> getList() {
        return list;
    }

    public void setList(List<FoodItemVo> list) {
        this.list = list;
    }
}
