package com.example.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品详情图片表
 */
@ApiModel(value="com-example-user-vo-Goods")
@Data
public class GoodsDetailVO {
    /**
     * 商品详情图id
     */
    @ApiModelProperty(value="商品详情图id")
    private Integer id;


    /**
     * 商品详情图的地址
     */
    @ApiModelProperty(value="商品详情图的地址")
    private String address;
}
