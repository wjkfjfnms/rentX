package com.example.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OrderItemVO {
    @ApiModelProperty(value="该商品在订单中租赁的数量")
    private int number;
    @ApiModelProperty(value="租期（天）")
    private int leaseTime;
    @ApiModelProperty(value="单项租金")
    private float itemPrice;
    @ApiModelProperty(value="单项押金")
    private float itemDeposit;
}
