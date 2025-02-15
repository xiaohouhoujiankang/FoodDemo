package org.example.foodDemo.app.controller;

import org.example.foodDemo.app.domain.FoodInfoVo;
import org.example.foodDemo.app.domain.FoodItemVo;
import org.example.foodDemo.app.domain.FoodListVo;
import org.example.foodDemo.module.entity.Food;
import org.example.foodDemo.module.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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
    public FoodListVo getFoodList() {
        List<Food> foods = foodService.selectAllFoods();
        List<FoodItemVo> voList = new ArrayList<>();
        for (int i = 0; i < foods.size(); i++) {
            Food food = foods.get(i);
            FoodItemVo vo = new FoodItemVo();
            vo.setFoodId(food.getId());
            vo.setFoodName(food.getName());
            vo.setFoodPhoto(food.getFoodPhotos().split("\\$")[0]);
            voList.add(vo);
        }
        return new FoodListVo(voList);
    }

}
