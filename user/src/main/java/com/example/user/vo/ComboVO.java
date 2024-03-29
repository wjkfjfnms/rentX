package com.example.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 套餐下拉列表
 */
@ApiModel(value="com-example-user-vo-ComboVO")
@Data
public class ComboVO {
    /**
     * 套餐id
     */
    @ApiModelProperty(value="套餐id")
    private Integer id;

    /**
     * 套餐名称/描述
     */
    @ApiModelProperty(value="套餐名称/描述")
    private String combo;
}
