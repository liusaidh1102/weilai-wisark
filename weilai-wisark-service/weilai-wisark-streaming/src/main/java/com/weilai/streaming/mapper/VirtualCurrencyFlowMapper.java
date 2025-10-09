package com.weilai.streaming.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.weilai.model.streaming.dos.VirtualCurrencyFlow;
import org.apache.ibatis.annotations.Mapper;

/**
 * 虚拟货币流水Mapper接口
 */
@Mapper
public interface VirtualCurrencyFlowMapper extends BaseMapper<VirtualCurrencyFlow> {
}