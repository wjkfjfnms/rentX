package com.example.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CreateOrderDTO {


    /**
     * 商家id
     */
    @ApiModelProperty(value="商家id（即商品信息的userId）")
    private Long businessid;



    /**
     * 收货地址表的主键id
     */
    @ApiModelProperty(value="收货地址表的主键id")
    private Integer addressid;

    /**
     * 备注
     */
    @ApiModelProperty(value="备注")
    private String remark;

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

    /**
     * 租期（天）
     */
    @ApiModelProperty(value="租期（天）")
    private Integer leasetime;


    @ApiModelProperty(value="成色id")
    private Integer qualityId;


    /**
     * 订单状态
     */
    @ApiModelProperty(value="订单状态不用传")
    private String state;

    @ApiModelProperty(value="套餐名字（不用传，这是返回的信息）")
    private String comboName;

    @ApiModelProperty(value="成色（不用传，这是返回的信息）")
    private String quality;

    /**
     * 订单主键
     */
    @ApiModelProperty(value="订单主键(不用传）")
    private Integer id;

    /**
     * 订单编号
     */
    @ApiModelProperty(value="订单编号（不用传）")
    private String ordernum;

    /**
     * 客户id
     */
    @ApiModelProperty(value="客户id(不用传)")
    private Long customerid;

    /**
     * 租金
     */
    @ApiModelProperty(value="租金（不用传）")
    private Double price;

    /**
     * 押金
     */
    @ApiModelProperty(value="押金(不用传)")
    private Double deposit;

    @ApiModelProperty(value="订单地址（不用传）")
    private String address;
}
