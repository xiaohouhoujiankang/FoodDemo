package org.example.food_demo.app.controller;

import org.example.food_demo.app.domain.CategoryItemVo;
import org.example.food_demo.module.entity.Category;
import org.example.food_demo.module.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
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
            vo.setParentId(category.getParentId());


            List<Category> subCategories = categoryService.selectSubCategories(category.getId(), keyWord);
            List<CategoryItemVo> subVoList = new ArrayList<>();

            for (int j = 0; j < subCategories.size(); j++) {
                Category subCategory = subCategories.get(j);
                CategoryItemVo subVo = new CategoryItemVo();
                subVo.setCategoryId(subCategory.getId());
                subVo.setCategoryName(subCategory.getName());
                subVo.setCategoryImage(subCategory.getImage());
                subVo.setParentId(subCategory.getParentId());
                subVoList.add(subVo);
            }
            vo.setSubCategories(subVoList);
            voList.add(vo);
        }

        return voList;

    }
}
