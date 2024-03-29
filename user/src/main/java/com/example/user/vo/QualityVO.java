package com.example.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品表
 */
@ApiModel(value="com-example-user-vo-QualityVO")
@Data
public class QualityVO {
    /**
     * 成色id
     */
    @ApiModelProperty(value="成色id")
    private Integer id;

    /**
     * 成色描述（99新，98新，95新，92新）
     */
    @ApiModelProperty(value="成色描述（99新，98新，95新，92新）")
    private String quality;
}
