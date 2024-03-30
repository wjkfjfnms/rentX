package com.example.user.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
    * 收藏夹
    */
@ApiModel(value="com-example-user-po-Favorites")
@Data
public class Favorites {
    /**
    * 收藏夹商品主键
    */
    @ApiModelProperty(value="收藏夹商品主键")
    private Integer id;

    /**
    * 商品id
    */
    @ApiModelProperty(value="商品id")
    private Integer goodsId;

    /**
    * 用户id
    */
    @ApiModelProperty(value="用户id")
    private Integer userId;
}
