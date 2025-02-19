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

    public List<Food> selectAllFoods() {
        return foodMapper.selectAllFoods();
    }

    public Food selectFoodById(BigInteger id) {
        return foodMapper.selectFoodById(id);
    }
    public int createFood(String name,String foodPhotos,String foodIntroduce){
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        Food food = new Food();
        food.setName(name);
        food.setFoodPhotos(foodPhotos);
        food.setFoodIntroduce(foodIntroduce);
        food.setCreateTime(timestamp);
        food.setUpdateTime(timestamp);
        food.setIsDeleted(0);
        return foodMapper.insert(food);
    }
    public int updateFood(BigInteger id,String name,String foodPhotos,String foodIntroduce){
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        Food food = new Food();
        food.setId(id);
        food.setName(name);
        food.setFoodPhotos(foodPhotos);
        food.setFoodIntroduce(foodIntroduce);
        food.setUpdateTime(timestamp);
        return foodMapper.update(food);
    }
    public int deleteFood(BigInteger id){
        return  foodMapper.delete(id,(int) (System.currentTimeMillis() / 1000));
    }

}

