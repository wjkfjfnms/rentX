package com.example.user.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PasswordLoginDTO {

    /**
     * 主键
     */
    @ApiModelProperty(value="主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户账号(邮箱)
     */
    @ApiModelProperty(value="用户账号(邮箱)", dataType = "String", required = true)
    @Schema(example = "xxx@qq.com")
    private String email;

    /**
     * 密码
     */
    @ApiModelProperty(value="密码", dataType = "String", required = true)
    @Schema(example = "password123")
    private String password;
}
