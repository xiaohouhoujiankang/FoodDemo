package org.example.foodDemo.console.controller;
import org.example.foodDemo.console.domain.ResultVo;
import org.example.foodDemo.module.service.FoodService;
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
   public ResultVo FoodCreate(@RequestParam(name = "name") String name,
                              @RequestParam(name = "foodPhotos") String foodPhotos,
                              @RequestParam(name = "foodIntroduce") String foodIntroduce){
      int result = service.createFood(name,foodPhotos,foodIntroduce);
      return new ResultVo(1 == result ?"成功":"失败");

   }
   @RequestMapping("/food/update")
   public ResultVo FoodUpdate(@RequestParam(name = "id") BigInteger id,
                            @RequestParam(name = "name") String name,
                            @RequestParam(name = "foodPhotos") String foodPhotos,
                            @RequestParam(name = "foodIntroduce") String foodIntroduce){
      int result = service.updateFood(id,name,foodPhotos,foodIntroduce);
      return new ResultVo(1 == result ?"成功":"失败");
   }
   @RequestMapping("/food/delete")
   public ResultVo FoodDelete(@RequestParam(name = "id") BigInteger id){
      int result = service.deleteFood(id);
      return new ResultVo(1 == result ?"成功":"失败");
   }

}
