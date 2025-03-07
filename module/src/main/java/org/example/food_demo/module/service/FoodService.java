package org.example.food_demo.module.service;

import org.example.food_demo.module.entity.Food;
import org.example.food_demo.module.mapper.FoodMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class FoodService {
    @Autowired
    private FoodMapper foodMapper;

    public Food extractById(BigInteger id) {
        return foodMapper.extractById(id);
    }

    public Food getById(BigInteger id) {
        return foodMapper.getById(id);
    }

    public int editFood(BigInteger id, String name, String foodPhotos, String foodIntroduce) {
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("name cannot be null or empty");
        }
        if (foodPhotos == null || foodPhotos.isEmpty()) {
            throw new RuntimeException("foodPhotos cannot be null or empty");
        }
        if (foodIntroduce == null || foodIntroduce.isEmpty()) {
            throw new RuntimeException("foodIntroduce cannot be null or empty");
        }
        Food food = new Food();
        food.setName(name);
        food.setFoodPhotos(foodPhotos);
        food.setFoodIntroduce(foodIntroduce);
        food.setCreateTime(timestamp);
        food.setUpdateTime(timestamp);
        food.setIsDeleted(0);
        if (id == null) {
            int newId = foodMapper.insert(food);
            return newId;
        } else {
            Food existingFood = foodMapper.getById(id);
            if (existingFood == null) {
                throw new RuntimeException("Food with id " + id + " does not exist");
            }
            food.setId(id);
            foodMapper.update(food);
            return id.intValue();
        }
    }



    public int deleteFood(BigInteger id) {
        return foodMapper.delete(id, (int) (System.currentTimeMillis() / 1000));
    }

    public List<Food> selectByLimit(Integer page, Integer pageSize, String keyWord) {
        int offset = (page - 1) * pageSize;
        return foodMapper.selectByLimit(offset, pageSize, keyWord);
    }

    public int getTotalCount() {
        return foodMapper.getTotalCount();
    }


}

