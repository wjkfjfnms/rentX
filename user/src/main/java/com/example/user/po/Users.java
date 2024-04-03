package com.example.user.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
    * 用户
    */
@ApiModel(value="com-example-user-po-Users")
@Data
@TableName("users")
public class Users implements Serializable {
    private static final long serialVersionUID = 4522943071576672084L;

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
    * 密码
    */
    @ApiModelProperty(value="密码")
    private String password;

    /**
     * 加密盐
     */
    @ApiModelProperty(value="加密盐")
    private String salt;


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
    @ApiModelProperty(value="头像")
    private String touxiang;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date createtime;

    /**
     * 身份
     */
    @ApiModelProperty(value="身份（ADMIN/USER/MERCHANTS  管理员、客户、商家）")
    private String role;

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
