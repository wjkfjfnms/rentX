package com.example.user.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
    * 分类表
    */
@ApiModel(value="com-example-user-po-Category")
@Data
public class Category {
    /**
    * 类别id
    */
    @ApiModelProperty(value="类别id")
    private Integer id;

    /**
    * 类别名称
    */
    @ApiModelProperty(value="类别名称")
    private String typename;

    /**
    * 1：已删除 0：未删除
    */
    @ApiModelProperty(value="1：已删除 0：未删除")
    private Integer state;
}