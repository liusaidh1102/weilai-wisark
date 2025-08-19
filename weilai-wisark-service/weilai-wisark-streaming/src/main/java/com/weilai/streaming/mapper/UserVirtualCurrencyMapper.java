package com.weilai.streaming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.streaming.dos.UserVirtualCurrency;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户虚拟货币Mapper接口
 */
@Mapper
public interface UserVirtualCurrencyMapper extends BaseMapper<UserVirtualCurrency> {
}