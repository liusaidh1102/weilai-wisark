package com.weilai.streaming.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.streaming.dos.UserVirtualCurrency;
import com.weilai.streaming.mapper.UserVirtualCurrencyMapper;
import com.weilai.streaming.service.UserVirtualCurrencyService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户虚拟货币服务实现类
 */
@Service
public class UserVirtualCurrencyServiceImpl extends ServiceImpl<UserVirtualCurrencyMapper, UserVirtualCurrency> implements UserVirtualCurrencyService {

    @Resource
    private UserVirtualCurrencyMapper userVirtualCurrencyMapper;

    @Override
    public List<UserVirtualCurrency> getAllUserVirtualCurrencies() {
        QueryWrapper<UserVirtualCurrency> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_at");
        return userVirtualCurrencyMapper.selectList(queryWrapper);
    }

    @Override
    public UserVirtualCurrency getUserVirtualCurrencyById(Long id) {
        return userVirtualCurrencyMapper.selectById(id);
    }

    @Override
    public void addUserVirtualCurrency(UserVirtualCurrency userVirtualCurrency) {
        userVirtualCurrencyMapper.insert(userVirtualCurrency);
    }

    @Override
    public void updateUserVirtualCurrency(UserVirtualCurrency userVirtualCurrency) {
        userVirtualCurrency.setUpdatedAt(new DateTime());
        userVirtualCurrencyMapper.updateById(userVirtualCurrency);
    }
}