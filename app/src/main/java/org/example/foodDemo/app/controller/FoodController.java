package org.example.foodDemo.app.controller;

import org.example.foodDemo.app.domain.FoodInfoVo;
import org.example.foodDemo.app.domain.FoodItemVo;
import org.example.foodDemo.app.domain.FoodListVo;
import org.example.foodDemo.module.entity.Food;
import org.example.foodDemo.module.mapper.FoodMapper;
import org.example.foodDemo.module.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigInteger;
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
        vo.setName(food.getName());
        vo.setFoodIntroduce(food.getFoodIntroduce());
        vo.setPageView(food.getViewCount());
        vo.setPublishTime(String.valueOf(food.getCreateTime()));
        vo.setSlideShow(food.getFoodPhotos().split("\\$"));
        return vo;
    }
    @RequestMapping("/food/list")
    public FoodListVo getFoodList() {
        List<Food> foods = foodService.selectAllFoods();
        List<FoodItemVo> voList = foods.stream().map(food -> {
            FoodItemVo vo = new FoodItemVo();
            vo.setId(food.getId());
            vo.setFoodName(food.getName());
            vo.setFoodPhoto(food.getFoodPhotos().split("\\$")[0]);
            return vo;
        }).collect(Collectors.toList());
        return new FoodListVo(voList);
    }

}
