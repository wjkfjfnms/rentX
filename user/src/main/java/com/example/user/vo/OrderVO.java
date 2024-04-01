package com.example.user.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class OrderVO {

//    -----------------------------------orders--------------------------------------------

    /**
     * 订单主键
     */
    @ApiModelProperty(value="订单主键")
    private Integer id;
    @ApiModelProperty(value="订单编号")
    private String orderNumber;
    @ApiModelProperty(value="总租金")
    private float price;
    @ApiModelProperty(value="总押金")
    private float deposit;
    @ApiModelProperty(value="备注")
    private String remark;
    @ApiModelProperty(value="创建时间")
    private LocalDateTime createTime;
    @ApiModelProperty(value="订单状态")
    private String status;
    @ApiModelProperty(value="其他信息")
    private List<OrderItemVO> orderItemsList;
    @ApiModelProperty(value="套餐名称")
    private String comboName;
    @ApiModelProperty(value="成色名称")
    private String quality;
    @ApiModelProperty(value="客户名称")
    private String customerNickname;
    @ApiModelProperty(value="收货人")
    private String consignee;
    @ApiModelProperty(value="收货人电话")
    private String phone;
    @ApiModelProperty(value="邮编")
    private String zip;
    @ApiModelProperty(value="收货地址")
    private String address;
}
