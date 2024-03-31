package com.example.user.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
    * 订单表
    */
@ApiModel(value="com-example-user-po-Orders")
@Data
public class Orders {
    /**
    * 订单主键
    */
    @ApiModelProperty(value="订单主键")
    private Integer id;

    /**
    * 订单编号
    */
    @ApiModelProperty(value="订单编号")
    private String ordernum;

    /**
    * 商家id
    */
    @ApiModelProperty(value="商家id")
    private String businessid;

    /**
    * 客户id
    */
    @ApiModelProperty(value="客户id")
    private String customerid;

    /**
    * 租金
    */
    @ApiModelProperty(value="租金")
    private Double price;

    /**
    * 押金
    */
    @ApiModelProperty(value="押金")
    private Double deposit;

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
    * 订单状态
    */
    @ApiModelProperty(value="订单状态")
    private Integer status;

    /**
    * 订单创建时间
    */
    @ApiModelProperty(value="订单创建时间")
    private Date createtime;
}