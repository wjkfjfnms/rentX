package com.example.user.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
    * 商品详情图片表
    */
@ApiModel(value="com-example-user-po-Goodsdetail")
@Data
public class Goodsdetail {
    /**
    * 商品详情图id
    */
    @ApiModelProperty(value="商品详情图id")
    private Integer id;

    /**
    * 该商品详情图所属的商品id
    */
    @ApiModelProperty(value="该商品详情图所属的商品id")
    private Integer goodsid;

    /**
    * 商品详情图的地址
    */
    @ApiModelProperty(value="商品详情图的地址")
    private String address;

    /**
    * 是否删除（1：已删除 0：未删除）
    */
    @ApiModelProperty(value="是否删除（1：已删除 0：未删除）")
    private Integer state;
}