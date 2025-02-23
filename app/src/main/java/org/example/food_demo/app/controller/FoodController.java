package org.example.food_demo.app.controller;

import org.example.food_demo.app.domain.FoodInfoVo;
import org.example.food_demo.app.domain.FoodItemVo;
import org.example.food_demo.app.domain.FoodListVo;
import org.example.food_demo.module.entity.Food;
import org.example.food_demo.module.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@RestController
public class FoodController {
    @Autowired
    private FoodService foodService;
    @RequestMapping("/food/info")
    public FoodInfoVo getFoodInfo(@RequestParam BigInteger foodId) {
        Food food = foodService.selectFoodById(foodId);
        FoodInfoVo vo = new FoodInfoVo();
        vo.setFoodName(food.getName());
        vo.setFoodIntroduce(food.getFoodIntroduce());
        vo.setPageView(food.getViewCount());
        vo.setPublishTime(String.valueOf(food.getCreateTime()));
        vo.setSlideShow(List.of(food.getFoodPhotos().split("\\$")));
        return vo;
    }
    @RequestMapping("/food/list")
    public FoodListVo getFoodList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize) {
        List<Food> foods = foodService.selectByLimit(page, pageSize);
        List<FoodItemVo> voList = new ArrayList<>();
        for (int i = 0; i < foods.size(); i++) {
            Food food = foods.get(i);
            FoodItemVo vo = new FoodItemVo();
            vo.setFoodId(food.getId());
            vo.setFoodName(food.getName());
            vo.setFoodPhoto(food.getFoodPhotos().split("\\$")[0]);
            voList.add(vo);
        }
        boolean isEnd =  foods.size() < pageSize;
        return new FoodListVo(voList,isEnd);
    }

}
