package com.example.user.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
    * 成色表
    */
@ApiModel(value="com-example-user-po-Quality")
@Data
public class Quality {
    /**
    * 成色id
    */
    @ApiModelProperty(value="成色id")
    private Integer id;

    /**
    * 所属商品id
    */
    @ApiModelProperty(value="所属商品id")
    private Integer goodsid;

    /**
    * 成色描述（99新，98新，95新，92新）
    */
    @ApiModelProperty(value="成色描述（99新，98新，95新，92新）")
    private String quality;

    /**
    * 是否删除（1：已删除 0：未删除）
    */
    @ApiModelProperty(value="是否删除（1：已删除 0：未删除）")
    private Integer state;
}