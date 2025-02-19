package org.example.food_demo.module.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.food_demo.module.entity.Food;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface FoodMapper {
    @Select("SELECT * FROM food WHERE is_deleted = 0")
    List<Food> selectAllFoods();

    @Select("SELECT * FROM food WHERE id = #{id} AND is_deleted = 0")
    Food selectFoodById(@Param("id") BigInteger id);

   int update(@Param("food") Food food);
   int insert(@Param("food") Food food);
   @Update("update food set is_deleted=1,update_time=#{time} where id=#{id} limit 1")
    int delete(@Param("id") BigInteger id,@Param("time") Integer time);
}
