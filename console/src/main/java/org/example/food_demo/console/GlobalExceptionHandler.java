package org.example.food_demo.console;

import org.example.food_demo.console.domain.ResultVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVo handleException(Exception e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
        return new ResultVo("系统异常");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public ResultVo handleNoHandlerFoundException(NoHandlerFoundException e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
        return new ResultVo("请求的资源未找到");
    }
}

