package com.example.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetEmailCodeDTO {

    @ApiModelProperty(value="账号邮箱")
    private String email;
    @ApiModelProperty(value="密码")
    private String password;
    @ApiModelProperty(value="权限码")
    private String code;
}
