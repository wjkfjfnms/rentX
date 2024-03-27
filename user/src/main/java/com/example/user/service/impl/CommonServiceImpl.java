package com.example.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.example.user.constant.RedisConstant;
import com.example.user.dao.UsersMapper;
import com.example.user.dto.GetEmailCodeDTO;
import com.example.user.enums.HttpStatusEnum;
import com.example.user.po.Users;
import com.example.user.service.CommonService;
import com.example.user.util.StringUtil;
import com.example.user.util.TokenUtils;
import com.example.user.vo.RE;
import com.example.user.vo.userVO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class CommonServiceImpl implements CommonService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ThreadService threadService;

    @Autowired
    TokenUtils tokenUtils;

    @Autowired
    UsersMapper usersMapper;

    @Override
    public RE getRequestPermissionCode(String emailJson) {
        try {
            // 非空校验
            if (StringUtils.isBlank(emailJson)) {
                return RE.error(HttpStatusEnum.PARAM_ILLEGAL);
            }

            // JSON转换，提取email的值
            String email = emailJson;
//            String email = JSON.parseObject(emailJson).getString("emailJson").trim();
            // 邮箱校验
            if (!StringUtil.checkEmail(email)) {
                return RE.error(HttpStatusEnum.EMAIL_ERROR);
            }

            // 随机生成权限码
            String permissionCode = UUID.randomUUID().toString();

            // 存入redis，缓存10s
            redisTemplate.opsForValue().set(RedisConstant.EMAIL_REQUEST_VERIFY + email, permissionCode, RedisConstant.EXPIRE_TEN_SECOND, TimeUnit.SECONDS);
            return RE.ok().data("permissionCode", permissionCode);
        } catch (JSONException e) {
            // 捕获 FastJSON 解析异常
            System.out.println("FastJSON 解析异常: " + e.getMessage());
            return RE.error();
        }
    }
//    @Override
//    public RE getRequestPermissionCode(String emailJson) {
//        // 非空校验
//        if (StringUtils.isBlank(emailJson)) {
//            return RE.error(HttpStatusEnum.PARAM_ILLEGAL);
//        }
//
//        // JSON转换，提取email的值
//        String email = JSON.parseObject(emailJson).getString("email").trim();
//        // 邮箱校验
//        if (!StringUtil.checkEmail(email)) {
//            return RE.error(HttpStatusEnum.EMAIL_ERROR);
//        }
//
//        // 随机生成权限码
//        String permissionCode = UUID.randomUUID().toString();
//
//        // 存入redis，缓存10s
//        redisTemplate.opsForValue().set(RedisConstant.EMAIL_REQUEST_VERIFY + email, permissionCode, RedisConstant.EXPIRE_TEN_SECOND, TimeUnit.SECONDS);
//        return RE.ok().data("permissionCode", permissionCode);
//    }

    @Override
    public RE sendEmailCode(GetEmailCodeDTO getEmailCodeDTO) {
        if (getEmailCodeDTO == null) {
            return RE.error(HttpStatusEnum.PARAM_ILLEGAL);
        }

        // 获取权限码和邮箱
        String email = getEmailCodeDTO.getEmail();
        String permissionCode = getEmailCodeDTO.getCode();
        // 参数校验
        if (StringUtils.isAnyBlank(email, permissionCode)) {
            return RE.error(HttpStatusEnum.PARAM_ILLEGAL);
        } else if (!StringUtil.checkEmail(email)) {
            // 邮箱校验
            return RE.error(HttpStatusEnum.EMAIL_ERROR);
        } else {
            // 权限码比对
            String rightCode = redisTemplate.opsForValue().get(RedisConstant.EMAIL_REQUEST_VERIFY + email);
            if (!permissionCode.equals(rightCode)) {
                // 不通过
                return RE.error(HttpStatusEnum.ILLEGAL_OPERATION);
            }
        }

        // 全部通过

        // 随机生成6位数字验证码
        String code = StringUtil.randomSixCode();

        // 正文内容
        String content = "亲爱的用户：\n" +
                "您此次的验证码为：\n\n" +
                code + "\n\n" +
                "此验证码5分钟内有效，请立即进行下一步操作。 如非你本人操作，请忽略此邮件。\n" +
                "感谢您的使用！";

        // 发送验证码
        threadService.sendSimpleMail(email, "您此次的验证码为：" + code, content);
        // 丢入缓存，设置5分钟过期
        redisTemplate.opsForValue().set(RedisConstant.EMAIL + email, code, RedisConstant.EXPIRE_FIVE_MINUTE, TimeUnit.SECONDS);
        return RE.ok();
    }

    @Override
    public String getTokenNickname() {
        // 获取当前的认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        userVO user = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String email = userDetails.getUsername();
            user = usersMapper.selectByEmail(email);
            System.out.println("当前用户ID: " + user.getId());
            System.out.println("当前用户昵称: " + user.getNickname());
        } else {
            System.out.println("当前认证信息为空");
        }
        return user.getNickname();
    }

    @Override
    public Users getUsersDetails() {
        // 获取当前的认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Users users = null;
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            System.out.println("authentication:" + authentication);
            UserDetails userDetails = null;
            try {
                userDetails = (UserDetails) authentication.getPrincipal();
            } catch (ClassCastException e) {
                // 处理类型转换异常
                e.printStackTrace();
            }
            String email = userDetails.getUsername();
            if (email!=null){
                // 获取当前用户的用户名
                users = usersMapper.selectDetailsByEmail(email);
            }else {
                System.out.println("获取当前登录用户的账号失败");
            }

        } else {
            System.out.println("当前认证信息为空");
        }
        return users;
    }
}


