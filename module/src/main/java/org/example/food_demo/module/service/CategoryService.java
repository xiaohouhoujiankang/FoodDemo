package org.example.food_demo.module.service;

import org.example.food_demo.module.entity.Category;
import org.example.food_demo.module.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;


    public Category extractById(BigInteger id) {
        return categoryMapper.extractById(id);
    }


    public Category getById(BigInteger id) {
        return categoryMapper.getById(id);
    }


    public BigInteger editCategory(BigInteger id, String name, String image) {
        if (name == null || name.isEmpty()) {
            throw new RuntimeException("Category name cannot be empty");
        }
        if (image == null || image.isEmpty()) {
            throw new RuntimeException("Category image cannot be empty");
        }

        int timestamp = (int) (System.currentTimeMillis() / 1000);
        Category category = new Category();
        category.setName(name);
        category.setImage(image);
        category.setUpdateTime(timestamp);

        if (id == null) {
            category.setCreateTime(timestamp);
            category.setIsDeleted(0);
            categoryMapper.insert(category);
        } else {
            Category existingCategory = categoryMapper.getById(id);
            if (existingCategory == null) {
                throw new RuntimeException("Category with id " + id + " does not exist");
            }
            category.setId(id);
            categoryMapper.update(category);
        }
        return category.getId();
    }


    public int deleteCategory(BigInteger id) {
        return categoryMapper.delete(id, (int) (System.currentTimeMillis() / 1000));
    }


    public List<Category> selectByLimit(Integer page, Integer pageSize, String keyWord) {
        int offset = (page - 1) * pageSize;
        return categoryMapper.selectByLimit(offset, pageSize, keyWord);
    }


    public int getTotalCount() {
        return categoryMapper.getTotalCount();
    }
}