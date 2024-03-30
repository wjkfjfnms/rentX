package com.example.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class FavoritesVO {
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
     * 商品名字
     */
    @ApiModelProperty(value="商品名字")
    private String goodsName;

    /**
     * 商品图片（1张）
     */
    @ApiModelProperty(value="商品图片（1张）")
    private String goodsPicture;

    /**
     * 单价（天）
     */
    @ApiModelProperty(value="单价（天）")
    private Double price;
}
