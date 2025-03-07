package org.example.food_demo.console.controller;
import org.example.food_demo.console.domain.FoodInfoVo;
import org.example.food_demo.console.domain.FoodItemVo;
import org.example.food_demo.console.domain.FoodListVO;
import org.example.food_demo.console.domain.ResultVo;
import org.example.food_demo.module.entity.Food;
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
public class FoodConsoleController {
   @Autowired
   private FoodService service;
  @RequestMapping("/food/edit")
   public ResultVo foodEdit(@RequestParam(name = "id") BigInteger id,
                            @RequestParam(name = "name") String name,
                            @RequestParam(name = "foodPhotos") String foodPhotos,
                            @RequestParam(name = "foodIntroduce") String foodIntroduce) {
      name = name.trim();
      foodIntroduce = foodIntroduce.trim();
      try {
          if (name == null || name.isEmpty()) {
              return new ResultVo("Name cannot be null or empty");
          }
          if (foodPhotos == null || foodPhotos.isEmpty()) {
              return new ResultVo("Food photos cannot be null or empty");
          }
          if (foodIntroduce == null || foodIntroduce.isEmpty()) {
              return new ResultVo("Food introduce cannot be null or empty");
          }
           service.editFood(id, name, foodPhotos, foodIntroduce);
          return new ResultVo("成功");
      } catch (RuntimeException e) {
          return new ResultVo(e.getMessage());
      }
  }
   @RequestMapping("/food/delete")
   public ResultVo foodDelete(@RequestParam(name = "id") BigInteger id) {
      int result = service.deleteFood(id);
      return new ResultVo(1 == result ? "成功" : "失败");
   }

   @RequestMapping("/food/info")
   public FoodInfoVo getFoodConsoleInfoVo(@RequestParam BigInteger foodId) {
       if (foodId == null ) {
           throw new RuntimeException("foodId cannot null");
       }
      Food food = service.getById(foodId);
       if (food == null) {
           throw new RuntimeException("Food with id " + foodId + " does not exist");
       }
      FoodInfoVo vo = new FoodInfoVo();
       String foodName = food.getName();
       if (foodName == null || foodName.trim().isEmpty()) {
           throw new RuntimeException("Food name cannot be null or empty");
       }
      vo.setFoodName(food.getName());
       String foodIntroduce = food.getFoodIntroduce();
       if (foodIntroduce == null || foodIntroduce.trim().isEmpty()) {
           throw new RuntimeException("Food introduce cannot be null or empty");
       }
      vo.setFoodIntroduce(food.getFoodIntroduce());
      vo.setPageView(food.getViewCount());
       Integer createTime = food.getCreateTime();
       if (createTime == null) {
           throw new RuntimeException("Create time cannot be null");
       }
      vo.setPublishTime(formatTimestamp(food.getCreateTime()));

      vo.setSlideShow(List.of(food.getFoodPhotos().split("\\$")));
      vo.setCreateTime(formatTimestamp(food.getCreateTime()));
      vo.setUpdateTime(formatTimestamp(food.getUpdateTime()));
      return vo;
   }
   private String formatTimestamp(Integer timestamp) {
       if (timestamp == null) {
           throw new RuntimeException("Timestamp cannot be null");
       }
      LocalDateTime dateTime = LocalDateTime.ofInstant(
              Instant.ofEpochSecond(timestamp.longValue()),
              ZoneId.systemDefault()
      );
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      return dateTime.format(formatter);
}

   @RequestMapping("/food/list")
   public FoodListVO getFoodList( @RequestParam(value = "page", defaultValue = "1") Integer page,
                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
      List<Food> foods = service.selectByLimit(page, pageSize,null);
      int total = service.getTotalCount();
      List<FoodItemVo> voList = new ArrayList<>();
      for (int i = 0; i < foods.size(); i++) {
         Food food = foods.get(i);
         FoodItemVo vo = new FoodItemVo();
         vo.setFoodId(food.getId());
         vo.setFoodName(food.getName());
         vo.setFoodPhoto(food.getFoodPhotos().split("\\$")[0]);
         voList.add(vo);
      }
      return new FoodListVO(voList,total,pageSize);

   }
}
