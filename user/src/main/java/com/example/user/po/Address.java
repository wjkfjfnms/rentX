package com.example.user.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
    * 收货地址表
    */
@ApiModel(value="com-example-user-po-Address")
@Data
public class Address {
    /**
    * 主键索引
    */
    @ApiModelProperty(value="主键索引")
    private Integer id;

    /**
    * 用户id，users表的id
    */
    @ApiModelProperty(value="用户id，users表的id")
    private Long userid;

    /**
    * 收货人
    */
    @ApiModelProperty(value="收货人")
    private String consignee;

    /**
    * 电话
    */
    @ApiModelProperty(value="电话")
    private String phone;

    /**
    * 邮编
    */
    @ApiModelProperty(value="邮编")
    private String zip;

    /**
    * 收件人地址
    */
    @ApiModelProperty(value="收件人地址")
    private String address;

    /**
     * 是否删除
     */
    @ApiModelProperty(value="是否删除")
    private Integer state;
}
