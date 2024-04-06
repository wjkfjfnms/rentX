package com.example.user.dto;

import com.example.user.po.Combo;
import com.example.user.po.Goodsdetail;
import com.example.user.po.Quality;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.util.List;

@Data
public class UploadGoodsDTO {

    /**
     * 商品id
     */
    @ApiModelProperty(value="商品id(不用传)")
    private Integer id;

    @ApiModelProperty(value = "所属商家id（不用传）")
    private Integer userId;

    /**
     * 商品名字
     */
    @ApiModelProperty(value="商品名字")
    private String goodsname;

    /**
     * 品牌
     */
    @ApiModelProperty(value="品牌")
    private String brand;

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
    @ApiModelProperty(value="不用传")
    private String goodspicture;

//    @ApiModelProperty(value="商品图片（1张）")
//    private MultipartFile multipartFile;


    /**
     * 类别
     */
    @ApiModelProperty(value="类别")
    private String category;

    /**
     * 类别
     */
    @ApiModelProperty(value="类别id(不用传)")
    private Integer categoryId;

    @ApiModelProperty(value="商品状态")
    private String state;

    @ApiModelProperty(value="套餐列表")
    private List<Combo> comboList;

    /**
     * 成色描述（99新，98新，95新，92新）
     */
    @ApiModelProperty(value="成色列表（99新，98新，95新，92新）")
    private List<Quality> qualityList;

//    @ApiModelProperty(value = "详情图片列表")
//    private List<Goodsdetail> goodsdetailList;



}
