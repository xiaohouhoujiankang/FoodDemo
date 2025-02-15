package org.example.foodDemo.console.domain;


import lombok.Data;

@Data
public class ResultVo {
    private String message;
    public ResultVo(String message) {
        this.message = message;
    }
}
