package org.example.food_demo.app.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public class Wp {
    @JSONField(name = "page")
    private Integer page;

    @JSONField(name = "pageSize")
    private Integer pageSize;

}
