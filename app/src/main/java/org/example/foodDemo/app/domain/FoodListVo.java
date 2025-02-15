package org.example.foodDemo.app.domain;



import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class FoodListVo {

    private List<FoodItemVo> list;

    public FoodListVo(List<FoodItemVo> list) {
        this.list = list;
    }

}