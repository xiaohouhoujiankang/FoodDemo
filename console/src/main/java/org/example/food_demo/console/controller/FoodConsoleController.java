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

   @RequestMapping("/food/create")
   public ResultVo foodCreate(@RequestParam(name = "name") String name,
                              @RequestParam(name = "foodPhotos") String foodPhotos,
                              @RequestParam(name = "foodIntroduce") String foodIntroduce) {
      name = name.trim();
      foodIntroduce = foodIntroduce.trim();
      int result = service.createFood(name, foodPhotos, foodIntroduce);
      return new ResultVo(1 == result ? "成功" : "失败");

   }

   @RequestMapping("/food/update")
   public ResultVo foodUpdate(@RequestParam(name = "id") BigInteger id,
                              @RequestParam(name = "name") String name,
                              @RequestParam(name = "foodPhotos") String foodPhotos,
                              @RequestParam(name = "foodIntroduce") String foodIntroduce) {
      name = name.trim();
      foodIntroduce = foodIntroduce.trim();
      int result = service.updateFood(id, name, foodPhotos, foodIntroduce);
      return new ResultVo(1 == result ? "成功" : "失败");
   }

   @RequestMapping("/food/delete")
   public ResultVo foodDelete(@RequestParam(name = "id") BigInteger id) {
      int result = service.deleteFood(id);
      return new ResultVo(1 == result ? "成功" : "失败");
   }

   @RequestMapping("/food/info")
   public FoodInfoVo getFoodConsoleInfoVo(@RequestParam BigInteger foodId) {
      Food food = service.getById(foodId);
      FoodInfoVo vo = new FoodInfoVo();
      vo.setFoodName(food.getName());
      vo.setFoodIntroduce(food.getFoodIntroduce());
      vo.setPageView(food.getViewCount());
      vo.setPublishTime(formatTimestamp(food.getCreateTime()));
      vo.setSlideShow(List.of(food.getFoodPhotos().split("\\$")));
      vo.setCreateTime(formatTimestamp((food.getCreateTime())));
      vo.setUpdateTime(formatTimestamp((food.getUpdateTime())));
      return vo;
   }
   private String formatTimestamp(String timestamp) {
      LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(timestamp)), ZoneId.systemDefault());
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
