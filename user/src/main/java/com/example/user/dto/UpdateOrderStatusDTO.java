package com.example.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateOrderStatusDTO {

    @ApiModelProperty(value = "订单id")
    private Integer id;

    @ApiModelProperty(value = "订单状态")
    private String status;
}
