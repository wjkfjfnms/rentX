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
    private String combo;

    /**
    * 是否删除（1：已删除  0：未删除）
    */
    @ApiModelProperty(value="是否删除（1：已删除  0：未删除）")
    private Integer state;
}
