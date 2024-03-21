package com.example.user.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetEmailCodeDTO {

    @ApiModelProperty(value="账号邮箱", dataType = "String", required = true)
    @Schema(example = "xxx@qq.com")
    private String email;
    @ApiModelProperty(value="密码", dataType = "String", required = true)
    @Schema(example = "password123")
    private String password;
    @ApiModelProperty(value="权限码", dataType = "String", required = true)
    @Schema(example = "xxxxxx")
    private String code;
}
