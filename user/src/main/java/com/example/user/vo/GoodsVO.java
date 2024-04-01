package com.example.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Date;
import java.util.List;

/**
 * 商品表
 */
@ApiModel(value="com-example-user-vo-Goods")
@Data
public class GoodsVO {
    /**
     * 商品id
     */
    @ApiModelProperty(value="商品id")
    private Integer id;

    @ApiModelProperty(value = "所属商家id")
    private Long userId;

    /**
     * 类别
     */
    @ApiModelProperty(value="类别")
    private String category;

    @ApiModelProperty(value = "类别id")
    private Integer categoryId;

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
     * 商品状态
     */
    @ApiModelProperty(value="商品状态")
    private String state;

    /**
     * 商品详情图片
     */
    @ApiModelProperty(value="商品详情图片")
    private List<GoodsDetailVO> goodsDetailVOList;

    /**
     * 成色
     */
    @ApiModelProperty(value="成色")
    private List<QualityVO> qualityVOList;

    /**
     * 套餐
     */
    @ApiModelProperty(value="套餐")
    private List<ComboVO> comboVOList;
    private Date uploadTime;

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
