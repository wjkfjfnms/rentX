package com.example.user.po;

import io.netty.util.internal.MacAddressUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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
    @ApiModelProperty(value="不用传")
    private String address;

    @ApiModelProperty(value="商品详情图")
    private MultipartFile file;

    /**
    * 商品状态
    */
    @ApiModelProperty(value="商品状态")
    private String state;
}
