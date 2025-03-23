package org.example.food_demo.module.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.example.food_demo.module.entity.Category;

import java.math.BigInteger;
import java.util.List;
@Mapper
public interface CategoryMapper {
    @Select("SELECT * FROM category WHERE id = #{id} AND is_deleted = 0")
    Category getById(@Param("id") BigInteger id);

    @Select("SELECT * FROM category WHERE id = #{id}")
    Category extractById(@Param("id") BigInteger id);
    int insert(@Param("category") Category category);
    int update(@Param("category") Category category);
    @Update("UPDATE category SET is_deleted = 1, update_time = #{time} WHERE id = #{id} LIMIT 1")
    int delete(@Param("id") BigInteger id, @Param("time") Integer time);
    List<Category> selectByLimit(@Param("offset") Integer offset, @Param("limit") Integer limit,@Param("keyWord") String keyWord);
    @Select("SELECT COUNT(*) FROM category WHERE is_deleted = 0")
    int getTotalCount();

    List<Category> selectCategories();
    List<Category> selectSubCategories(@Param("parentId") BigInteger parentId);

}
