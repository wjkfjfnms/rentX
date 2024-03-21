package com.example.user.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class QuanxianmaDTO {
    /**
     * 权限码
     */
    @ApiModelProperty(value="权限码", dataType = "String", required = true)
    @Schema(example = "xxxxx")
    private String emailJson;
}
