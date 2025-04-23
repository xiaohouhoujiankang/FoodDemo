package org.example.food_demo.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.food_demo.app.domain.*;
import org.example.food_demo.app.domain.Wp;
import org.example.food_demo.app.util.WpUtils;
import org.example.food_demo.module.entity.Category;
import org.example.food_demo.module.entity.Food;
import org.example.food_demo.module.service.CategoryService;
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
    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/food/info")
    public FoodInfoVo getFoodInfo(@RequestParam BigInteger foodId) {
        Food food = foodService.getById(foodId);
        if (food == null) {
            FoodInfoVo info = new FoodInfoVo();
            info.setError("Food not found");
            return info;
        }
        BigInteger categoryId = food.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if (category == null) {
            FoodInfoVo info = new FoodInfoVo();
            info.setError("category not found");
            return info;
        }
        FoodInfoVo vo = new FoodInfoVo();
        vo.setFoodName(food.getName());
        vo.setFoodIntroduce(food.getFoodIntroduce());
        vo.setPageView(food.getViewCount());
        vo.setPublishTime(formatTimestamp(String.valueOf(food.getCreateTime())));
        vo.setSlideShow(List.of(food.getFoodPhotos().split("\\$")));
        vo.setCategoryName(category.getName());
        vo.setCategoryImage(category.getImage());
        return vo;
    }

    private String formatTimestamp(String timestamp) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(timestamp)), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }

    @RequestMapping("/food/list")
    public FoodListVo getFoodList(
            @RequestParam(value = "wp", required = false) String wpJson,
            @RequestParam(value = "keyword", required = false) String keyword) {
        Wp wp = WpUtils.fromBase64Json(wpJson, Wp.class);
        if (wp == null) {
            wp = new Wp();
            wp.setPage(1);
            wp.setPageSize(5);
        }
        if (keyword != null && !keyword.isEmpty()) {
            wp.setKeyword(keyword);
        }
        Integer page = wp.getPage();
        Integer pageSize = wp.getPageSize();

        List<Food> foods = foodService.selectByLimit(page, pageSize, wp.getKeyword());
        List<FoodItemVo> voList = new ArrayList<>();
        for (int i = 0; i < foods.size(); i++) {
            Food food = foods.get(i);
            FoodItemVo vo = new FoodItemVo();
            BigInteger categoryId = food.getCategoryId();
            Category category = categoryService.getById(categoryId);
            if (category == null) {
                continue;
            }
            vo.setCategoryName(category.getName());
            vo.setFoodId(food.getId());
            vo.setFoodName(food.getName());
            vo.setFoodPhoto(food.getFoodPhotos().split("\\$")[0]);
            voList.add(vo);

        }
        boolean isEnd = foods.size() < pageSize;
        String encodedWp = WpUtils.toBase64Json(wp);
        return new FoodListVo(voList, isEnd,encodedWp);
    }


}
