package org.example.food_demo.console.controller;
import org.example.food_demo.console.domain.ResultVo;
import org.example.food_demo.module.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;


@RestController
public class FoodConsoleController {
   @Autowired
   private FoodService service;
   @RequestMapping("/food/create")
   public ResultVo foodCreate(@RequestParam(name = "name") String name,
                              @RequestParam(name = "foodPhotos") String foodPhotos,
                              @RequestParam(name = "foodIntroduce") String foodIntroduce){
      name = name.trim();
      foodIntroduce = foodIntroduce.trim();
      int result = service.createFood(name,foodPhotos,foodIntroduce);
      return new ResultVo(1 == result ?"成功":"失败");

   }
   @RequestMapping("/food/update")
   public ResultVo foodUpdate(@RequestParam(name = "id") BigInteger id,
                            @RequestParam(name = "name") String name,
                            @RequestParam(name = "foodPhotos") String foodPhotos,
                            @RequestParam(name = "foodIntroduce") String foodIntroduce){
      name = name.trim();
      foodIntroduce = foodIntroduce.trim();
      int result = service.updateFood(id,name,foodPhotos,foodIntroduce);
      return new ResultVo(1 == result ?"成功":"失败");
   }
   @RequestMapping("/food/delete")
   public ResultVo foodDelete(@RequestParam(name = "id") BigInteger id){
      int result = service.deleteFood(id);
      return new ResultVo(1 == result ?"成功":"失败");
   }

}
