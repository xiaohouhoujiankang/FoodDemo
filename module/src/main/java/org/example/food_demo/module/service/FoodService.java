package org.example.food_demo.module.service;

import org.example.food_demo.module.entity.Category;
import org.example.food_demo.module.entity.Food;
import org.example.food_demo.module.mapper.CategoryMapper;
import org.example.food_demo.module.mapper.FoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService {
    @Autowired
    private FoodMapper foodMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    public Food extractById(BigInteger id) {
        return foodMapper.extractById(id);
    }

    public Food getById(BigInteger id) {
        return foodMapper.getById(id);
    }

    public BigInteger editFood(BigInteger id, String name, String foodPhotos, String foodIntroduce,BigInteger categoryId) {
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("Food name cannot be empty");
        }
        if (foodPhotos == null || foodPhotos.isEmpty()) {
            throw new RuntimeException("Food photos cannot be empty");
        }
        if (foodIntroduce == null || foodIntroduce.isEmpty()) {
            throw new RuntimeException("Food introduction cannot be empty");
        }
        Category category = categoryMapper.getById(categoryId);
        if (category == null) {
            throw new RuntimeException("Category ID does not exist");
        }

        int timestamp = (int) (System.currentTimeMillis() / 1000);
        Food food = new Food();
        food.setName(name);
        food.setFoodPhotos(foodPhotos);
        food.setFoodIntroduce(foodIntroduce);
        food.setUpdateTime(timestamp);

        if (id == null) {
            food.setCreateTime(timestamp);
            food.setIsDeleted(0);
            foodMapper.insert(food);
        } else {
            Food existingFood = foodMapper.getById(id);
            if (existingFood == null) {
                throw new RuntimeException("Food with id " + id + " does not exist");
            }
            food.setId(id);
            foodMapper.update(food);
        }
        return food.getId();
    }


    public int deleteFood(BigInteger id) {
        return foodMapper.delete(id, (int) (System.currentTimeMillis() / 1000));
    }

    public List<Food> selectByLimit(Integer page, Integer pageSize, String keyword) {
        int offset = (page - 1) * pageSize;
        List<Integer> categoryIds = foodMapper.getCategoryIds(keyword);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < categoryIds.size(); i++) {
            sb.append(categoryIds.get(i));
            if (i < categoryIds.size() - 1) {
                sb.append(",");
            }
        }
        String categoryIdsStr = sb.toString();
        return foodMapper.searchFood(keyword, categoryIdsStr, offset, pageSize);
    }


    public int getTotalCount() {
        return foodMapper.getTotalCount();
    }


}

