package org.example.foodDemo.console.domain;


import lombok.Data;


public class ResultVo {
    private String message;
    public ResultVo(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
