package org.example.food_demo.app.domain;



import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class FoodListVo {

    private List<FoodItemVo> list;
    private Boolean isEnd;
    private String wp;

    public FoodListVo(List<FoodItemVo> list, Boolean isEnd, String wp) {
        this.list = list;
        this.isEnd = isEnd;
        this.wp = wp;
    }


}