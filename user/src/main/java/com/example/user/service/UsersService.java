package com.example.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.user.dto.*;
import com.example.user.po.Users;
import com.example.user.vo.RE;

public interface UsersService extends IService<Users>{

    /**
     * 注册
     * @param registerDTO (邮箱、密码、验证码、身份)
     * @return 主键id
     */
    RE insert(RegisterDTO registerDTO);

    /**
     * 密码登录
     * @param passwordLoginDTO (邮箱和密码)
     * @return
     */
    RE passwordLogin(PasswordLoginDTO passwordLoginDTO);

    /**
     * 验证码登录
     * @param codeLoginDTO (邮箱和密码)
     * @return
     */
    RE codeLogin(CodeLoginDTO codeLoginDTO);

    /**
     * 找回密码
     * @param getEmailCodeDTO (邮箱、密码、验证码)
     * @return
     */
    RE findPassword(GetEmailCodeDTO getEmailCodeDTO);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Long id);

//    修改用户信息
    RE updateByPrimaryKeySelective(UpdateUserDTO updateUserDTO);

    int updateByPrimaryKey(Users record);

    int deleteByPrimaryKey(Long id);

}
