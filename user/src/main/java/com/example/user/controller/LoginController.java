package com.example.user.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.user.dto.*;
import com.example.user.service.CommonService;
import com.example.user.service.UsersService;
import com.example.user.vo.RE;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/user")
@Api(tags = {"登录注册接口"})
public class LoginController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private CommonService commonService;


    @ApiOperation(value = "注册")
    @PostMapping("/register")
    public RE register(@RequestBody RegisterDTO registerDTO) {
        return usersService.insert(registerDTO);
    }

    @ApiOperation(value = "密码登录")
    @PostMapping("/passwordLogin")
    public RE passwordLogin(@Validated @RequestBody PasswordLoginDTO passwordLoginDTO){
        return usersService.passwordLogin(passwordLoginDTO);
    }

    @ApiOperation(value = "验证码登录")
    @PostMapping("/codeLogin")
    public RE codeLogin(@Validated @RequestBody CodeLoginDTO codeLoginDTO){
        return usersService.codeLogin(codeLoginDTO);
    }

    @ApiOperation(value = "找回密码")
    @PostMapping("/findPassword")
    public RE findPassword(@Validated @RequestBody GetEmailCodeDTO getEmailCodeDTO){
        return usersService.findPassword(getEmailCodeDTO);
    }


    @ApiOperation(value = "权限码请求接口")
    @PostMapping("code/getRequestPermissionCode")
    public RE getRequestPermissionCode(@RequestBody QuanxianmaDTO quanxianmaDTO) {
        return commonService.getRequestPermissionCode(quanxianmaDTO.getEmailJson());
    }


    @ApiOperation(value = "邮箱验证码接口")
    @PostMapping("code/sendEmailCode")
    public RE sendEmailCode(@RequestBody GetEmailCodeDTO getEmailCodeDTO) {
        return commonService.sendEmailCode(getEmailCodeDTO);
    }
}
