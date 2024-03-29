package com.example.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value="com-example-user-dto-Goods")
@Data
public class GoodsDetailSelectDTO {
    /**
     * 商品id
     */
    @ApiModelProperty(value="商品id")
    private Integer id;
}
