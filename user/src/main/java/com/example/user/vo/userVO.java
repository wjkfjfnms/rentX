package com.example.user.vo;

import lombok.Data;

import java.util.Date;

@Data
public class userVO {
    private Long id;
    private String uid;
    private String name;
    private String password;
    private String sex;
    private String phone;
    private String touxiang;
    private Float money;
    private Date createtime;
}
