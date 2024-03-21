package com.example.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.user.constant.RedisConstant;
import com.example.user.dto.GetEmailCodeDTO;
import com.example.user.enums.HttpStatusEnum;
import com.example.user.service.CommonService;
import com.example.user.util.StringUtil;
import com.example.user.vo.RE;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class CommonServiceImpl implements CommonService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private ThreadService threadService;

    @Override
    public RE getRequestPermissionCode(String emailJson) {
        // 非空校验
        if (StringUtils.isBlank(emailJson)) {
            return RE.error(HttpStatusEnum.PARAM_ILLEGAL);
        }

        // JSON转换，提取email的值
        String email = JSON.parseObject(emailJson).getString("email").trim();
        // 邮箱校验
        if (!StringUtil.checkEmail(email)) {
            return RE.error(HttpStatusEnum.EMAIL_ERROR);
        }

        // 随机生成权限码
        String permissionCode = UUID.randomUUID().toString();

        // 存入redis，缓存10s
        redisTemplate.opsForValue().set(RedisConstant.EMAIL_REQUEST_VERIFY + email, permissionCode, RedisConstant.EXPIRE_TEN_SECOND, TimeUnit.SECONDS);
        return RE.ok().data("permissionCode", permissionCode);
    }

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
        }else if (!StringUtil.checkEmail(email)) {
            // 邮箱校验
            return RE.error(HttpStatusEnum.EMAIL_ERROR);
        }else {
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
}

