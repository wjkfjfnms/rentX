package com.example.user.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    /**
     * 主键
     */
    @ApiModelProperty(value="主键")
    @TableId(value = "id")
    private Long id;

    /**
     * 用户账号(邮箱)
     */
    @ApiModelProperty(value="用户账号(邮箱)", dataType = "String", required = true)
    @NotEmpty(message = "用户名不可为空")
    @Schema(example = "xxx@qq.com")
    private String email;

    /**
     * 验证码
     */
    @ApiModelProperty(value="验证码", dataType = "String", required = true)
    @NotEmpty(message = "验证码不可为空")
    @Schema(example = "123456")
    private String code;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不可为空")
    @Schema(example = "password123")
    @ApiModelProperty(value="密码", dataType = "String", required = true)
    private String password;

    /**
     * 加密盐
     */
    @ApiModelProperty(value="加密盐", dataType = "String", required = false)
    private String salt;

    /**
     * 身份（1：商家  2：租户）
     */
    @ApiModelProperty(value="身份（ADMIN/USER）", dataType = "String", required = true)
    @Schema(example = "ADMIN")
    private String role;

}
