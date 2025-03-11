package org.example.food_demo.app.controller;

import org.example.food_demo.app.domain.CategoryItemVo;
import org.example.food_demo.app.domain.FoodInfoVo;
import org.example.food_demo.app.domain.FoodItemVo;
import org.example.food_demo.app.domain.FoodListVo;
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
        FoodInfoVo vo = new FoodInfoVo();
        vo.setFoodName(food.getName());
        vo.setFoodIntroduce(food.getFoodIntroduce());
        vo.setPageView(food.getViewCount());
        vo.setPublishTime(formatTimestamp(String.valueOf(food.getCreateTime())));
        vo.setSlideShow(List.of(food.getFoodPhotos().split("\\$")));
        BigInteger categoryId = food.getCategoryId();
        Category category = categoryService.getById(categoryId);
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
    public FoodListVo getFoodList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                  @RequestParam(value = "keyWord", required = false) String keyWord) {
        List<Food> foods = foodService.selectByLimit(page, pageSize, keyWord);
        List<FoodItemVo> voList = new ArrayList<>();
        for (int i = 0; i < foods.size(); i++) {
            Food food = foods.get(i);
            FoodItemVo vo = new FoodItemVo();
            vo.setFoodId(food.getId());
            vo.setFoodName(food.getName());
            vo.setFoodPhoto(food.getFoodPhotos().split("\\$")[0]);
            BigInteger categoryId = food.getCategoryId();
            Category category = categoryService.getById(categoryId);
            vo.setCategoryName(category.getName());
            voList.add(vo);

        }
        boolean isEnd = foods.size() < pageSize;
        return new FoodListVo(voList, isEnd);
    }

    @RequestMapping("/category/list")
    public List<CategoryItemVo> getCategoryList(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                                @RequestParam(value = "keyWord", required = false) String keyWord) {
        List<Category> categories = categoryService.selectByLimit(page, pageSize, keyWord);
        List<CategoryItemVo> voList = new ArrayList<>();

        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            CategoryItemVo vo = new CategoryItemVo();
            vo.setCategoryId(category.getId());
            vo.setCategoryName(category.getName());
            vo.setCategoryImage(category.getImage());
            voList.add(vo);
        }

        return voList;

    }
}
