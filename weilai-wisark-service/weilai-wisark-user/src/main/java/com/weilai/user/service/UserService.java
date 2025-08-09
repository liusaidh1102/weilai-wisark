package com.weilai.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.weilai.common.response.Result;
import com.weilai.model.user.dos.User;
import com.weilai.model.user.dtos.EmailLoginDTO;

public interface UserService extends IService<User> {

    /**
     * 手机号登录
     * @param email
     * @param code
     * @return
     */
    Result<?> loginByCode(String email, String code);


    /**
     * 密码登录
     * @param
     * @param
     * @return
     */
    Result<?> loginByPwd(EmailLoginDTO emailLoginDTO);

    /**
     * 回调接口，获取用户信息
     * @param code
     * @param state
     * @return
     */
    Result<?> wechatLoginCallback(String code, String state);
}