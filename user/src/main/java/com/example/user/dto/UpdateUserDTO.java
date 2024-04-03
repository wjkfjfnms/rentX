package com.example.user.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@Data
public class UpdateUserDTO {
    /**
     * 主键
     */
    @ApiModelProperty(value="主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户账号(邮箱)
     */
    @ApiModelProperty(value="用户账号(邮箱)")
    private String email;


    /**
     * 性别
     */
    @ApiModelProperty(value="性别")
    private String sex;

    /**
     * 联系方式
     */
    @ApiModelProperty(value="联系方式")
    private String phone;

    /**
     * 头像
     */
    @ApiModelProperty(value="头像(不传这里)")
    private String touxiang;

    @ApiModelProperty(value="头像(传这里)")
    private MultipartFile multipartFile;


    /**
     * 昵称（10个字以内)
     */
    @ApiModelProperty(value="昵称（10个字以内)")
    private String nickname;

    @ApiModelProperty(value="真实姓名")
    private String realname;

    @ApiModelProperty(value="身份证号码")
    private String idnumber;
}
