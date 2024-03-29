package com.example.user.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
    * 商品表
    */
@ApiModel(value="com-example-user-po-Goods")
@Data
public class Goods {
    /**
    * 商品id
    */
    @ApiModelProperty(value="商品id")
    private Integer id;

    /**
    * 类别
    */
    @ApiModelProperty(value="类别")
    private String category;

    /**
    * 品牌
    */
    @ApiModelProperty(value="品牌")
    private String brand;

    /**
    * 商品名字
    */
    @ApiModelProperty(value="商品名字")
    private String goodsname;

    /**
    * 商品介绍
    */
    @ApiModelProperty(value="商品介绍")
    private String description;

    /**
    * 单价（天）
    */
    @ApiModelProperty(value="单价（天）")
    private Double price;

    /**
    * 是否支持买断（1：支持  0：不支持）
    */
    @ApiModelProperty(value="是否支持买断（1：支持  0：不支持）")
    private Integer buyout;

    /**
    * 买断价格
    */
    @ApiModelProperty(value="买断价格")
    private Double buyoutprice;

    /**
    * 商品图片（1张）
    */
    @ApiModelProperty(value="商品图片（1张）")
    private String goodspicture;

    /**
    * 被收藏数
    */
    @ApiModelProperty(value="被收藏数")
    private Integer favorites;

    /**
    * 是否删除（1：已删除  0：未删除）
    */
    @ApiModelProperty(value="是否删除（1：已删除  0：未删除）")
    private Integer state;
}