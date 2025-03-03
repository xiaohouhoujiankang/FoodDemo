package org.example.food_demo.module.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.food_demo.module.entity.Food;
import org.example.food_demo.module.mapper.FoodMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Service
public class FoodService extends ServiceImpl<FoodMapper, Food> {

    @Resource
    private FoodMapper foodMapper;

    public Food getById(BigInteger id) {
        QueryWrapper<Food> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id)
                .eq("is_deleted", 0);
        return foodMapper.selectOne(queryWrapper);
    }

    public Food extractById(BigInteger id) {
        return foodMapper.selectById(id);
    }

    public int createFood(String name, String foodPhotos, String foodIntroduce) {
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        Food food = new Food();
        food.setName(name);
        food.setFoodPhotos(foodPhotos);
        food.setFoodIntroduce(foodIntroduce);
        food.setCreateTime(String.valueOf(timestamp));
        food.setUpdateTime(String.valueOf(timestamp));
        food.setIsDeleted(0);
        return foodMapper.insert(food);
    }

    public int updateFood(BigInteger id, String name, String foodPhotos, String foodIntroduce) {
        int timestamp = (int) (System.currentTimeMillis() / 1000);
        Food food = new Food();
        food.setId(id);
        food.setName(name);
        food.setFoodPhotos(foodPhotos);
        food.setFoodIntroduce(foodIntroduce);
        food.setUpdateTime(String.valueOf(timestamp));
        UpdateWrapper<Food> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        return foodMapper.update(food, updateWrapper);
    }

    public int deleteFood(BigInteger id) {
        UpdateWrapper<Food> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", id);
        updateWrapper.set("is_deleted", 1);
        updateWrapper.set("update_time", (int) (System.currentTimeMillis() / 1000));
        return foodMapper.update(null, updateWrapper);
    }

    public List<Food> selectByLimit(Integer page, Integer pageSize, String keyWord) {
        Page<Food> pageObj = new Page<>(page, pageSize);
        QueryWrapper<Food> queryWrapper = new QueryWrapper<>();
        if (keyWord != null && !keyWord.isEmpty()) {
            queryWrapper.like("name", keyWord);
        }
        queryWrapper.eq("is_deleted", 0);
        queryWrapper.orderByAsc("id");
        Page<Food> resultPage = foodMapper.selectPage(pageObj, queryWrapper);
        return resultPage.getRecords();
    }



    public Long getTotalCount() {
        QueryWrapper<Food> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        return foodMapper.selectCount(queryWrapper);
    }
}