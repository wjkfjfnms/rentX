package com.example.user.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
    * 订单商品关联表
    */
@ApiModel(value="com-example-user-po-Orderitems")
@Data
public class Orderitems {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Integer id;

    /**
    * 订单表中的订单ID，表示该商品属于哪个订单
    */
    @ApiModelProperty(value="订单表中的订单ID，表示该商品属于哪个订单")
    private Integer orderid;

    /**
    * 商品表中的商品ID，表示租赁的具体商品
    */
    @ApiModelProperty(value="商品表中的商品ID，表示租赁的具体商品")
    private Integer goodsid;

    /**
    * 该商品在订单中租赁的数量
    */
    @ApiModelProperty(value="该商品在订单中租赁的数量")
    private Integer number;

    /**
    * 单项租金
    */
    @ApiModelProperty(value="单项租金")
    private Double itemprice;

    /**
    * 单项押金
    */
    @ApiModelProperty(value="单项押金")
    private Double itemdeposit;

    /**
    * 套餐id
    */
    @ApiModelProperty(value="套餐id")
    private Integer comboid;

    @ApiModelProperty(value="成色id")
    private Integer qualityId;

    /**
    * 租期（天）
    */
    @ApiModelProperty(value="租期（天）")
    private Integer leasetime;

    /**
    * 订单状态
    */
    @ApiModelProperty(value="订单状态")
    private String state;
}
