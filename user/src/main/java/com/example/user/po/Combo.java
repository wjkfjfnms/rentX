package com.example.user.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
    * 套餐表
    */
@ApiModel(value="com-example-user-po-ComboVO")
@Data
public class Combo {
    /**
    * 套餐id
    */
    @ApiModelProperty(value="套餐id")
    private Integer id;

    /**
    * 套餐所属商品的id
    */
    @ApiModelProperty(value="套餐所属商品的id")
    private Integer goodsid;

    /**
    * 套餐名称/描述
    */
    @ApiModelProperty(value="套餐名称/描述")
    private String comboName;

    /**
     * 库存
     */
    @ApiModelProperty(value="库存")
    private Integer inventory;

    /**
    * 套餐状态
    */
    @ApiModelProperty(value="套餐状态")
    private String state;
}
