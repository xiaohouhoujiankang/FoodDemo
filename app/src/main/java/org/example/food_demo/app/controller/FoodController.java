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
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@RestController
public class FoodController {
    @Autowired
    private FoodService foodService;

    @RequestMapping("/food/info")
    public FoodInfoVo getFoodInfo(@RequestParam BigInteger foodId) {
        if (foodId == null ) {
            throw new RuntimeException("foodId cannot null");
        }
        Food food = foodService.getById(foodId);

        if (food == null) {
            throw new RuntimeException("Food with id " + foodId + " does not exist");
        }
        FoodInfoVo vo = new FoodInfoVo();

        String foodName = food.getName();
        if (foodName == null || foodName.trim().isEmpty()) {
            throw new RuntimeException("Food name cannot be null or empty");
        }
        vo.setFoodName(food.getName());

        String foodIntroduce = food.getFoodIntroduce();
        if (foodIntroduce == null || foodIntroduce.trim().isEmpty()) {
            throw new RuntimeException("Food introduce cannot be null or empty");
        }
        vo.setFoodIntroduce(food.getFoodIntroduce());

        Integer pageView = food.getViewCount();
        if (pageView == null) {
            throw new RuntimeException("Page view count cannot be null");
        }
        vo.setPageView(food.getViewCount());

        Integer createTime = food.getCreateTime();
        if (createTime == null) {
            throw new RuntimeException("Create time cannot be null");
        }
        vo.setPublishTime(formatTimestamp(String.valueOf(food.getCreateTime())));

        String foodPhotos = food.getFoodPhotos();
        if (foodPhotos == null || foodPhotos.trim().isEmpty()) {
            throw new RuntimeException("Food photos cannot be null or empty");
        }
        vo.setSlideShow(List.of(food.getFoodPhotos().split("\\$")));
        return vo;
    }
    private String formatTimestamp(String timestamp) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(timestamp)), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    @RequestMapping("/food/list")
    public FoodListVo getFoodList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                  @RequestParam(value = "keyWord", required = false) String keyWord) {
        List<Food> foods = foodService.selectByLimit(page, pageSize,keyWord);
        if (foods == null) {
            throw new RuntimeException("Failed to retrieve food list");
        }

        List<FoodItemVo> voList = new ArrayList<>();
        for (int i = 0; i < foods.size(); i++) {
            Food food = foods.get(i);
            FoodItemVo vo = new FoodItemVo();
            vo.setFoodId(food.getId());
            vo.setFoodName(food.getName());
            vo.setFoodPhoto(food.getFoodPhotos().split("\\$")[0]);
            voList.add(vo);
        }
        boolean isEnd = foods.size() < pageSize;
        return new FoodListVo(voList, isEnd);
    }

}
