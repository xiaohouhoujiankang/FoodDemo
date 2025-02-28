package org.example.food_demo.app.domain;



import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class FoodListVo {

    private List<FoodItemVo> list;
    private Boolean isEnd;


    public FoodListVo(List<FoodItemVo> list,Boolean isEnd) {
        this.list = list;
        this.isEnd = isEnd;
    }


}