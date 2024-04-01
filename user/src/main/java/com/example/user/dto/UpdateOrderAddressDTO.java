package com.example.user.dto;

import com.example.user.po.Address;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateOrderAddressDTO {

    @ApiModelProperty(value = "订单id")
    private Integer id;

    @ApiModelProperty(value = "地址信息")
    private Integer addressid;
}
