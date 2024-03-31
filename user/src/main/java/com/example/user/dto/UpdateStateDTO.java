package com.example.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateStateDTO {

    /**
     * 商品id
     */
    @ApiModelProperty(value="商品id")
    private Integer id;

    /**
     * 商品状态
     */
    @ApiModelProperty(value="商品状态")
    private String state;
}
