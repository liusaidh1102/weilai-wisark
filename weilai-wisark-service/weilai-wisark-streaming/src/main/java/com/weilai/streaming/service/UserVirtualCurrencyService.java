package com.weilai.streaming.service;

import com.weilai.model.streaming.dos.UserVirtualCurrency;

import java.util.List;

/**
 * 用户虚拟货币服务接口
 */
public interface UserVirtualCurrencyService {

    /**
     * 获取所有用户虚拟货币信息
     *
     * @return 用户虚拟货币信息列表
     */
    List<UserVirtualCurrency> getAllUserVirtualCurrencies();

    /**
     * 根据ID获取用户虚拟货币信息
     *
     * @param id 用户虚拟货币信息ID
     * @return 用户虚拟货币信息详情
     */
    UserVirtualCurrency getUserVirtualCurrencyById(Long id);

    /**
     * 添加用户虚拟货币信息
     *
     * @param userVirtualCurrency 用户虚拟货币信息
     */
    void addUserVirtualCurrency(UserVirtualCurrency userVirtualCurrency);

    /**
     * 更新用户虚拟货币信息
     *
     * @param userVirtualCurrency 用户虚拟货币信息
     */
    void updateUserVirtualCurrency(UserVirtualCurrency userVirtualCurrency);
}