package com.weilai.streaming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.streaming.dos.VirtualCurrencyFlow;
import com.weilai.streaming.mapper.VirtualCurrencyFlowMapper;
import com.weilai.streaming.service.VirtualCurrencyFlowService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 虚拟货币流水服务实现类
 */
@Service
public class VirtualCurrencyFlowServiceImpl extends ServiceImpl<VirtualCurrencyFlowMapper, VirtualCurrencyFlow> implements VirtualCurrencyFlowService {

    @Resource
    private VirtualCurrencyFlowMapper virtualCurrencyFlowMapper;

    @Override
    public List<VirtualCurrencyFlow> getAllVirtualCurrencyFlows() {
        QueryWrapper<VirtualCurrencyFlow> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_at");
        return virtualCurrencyFlowMapper.selectList(queryWrapper);
    }

    @Override
    public VirtualCurrencyFlow getVirtualCurrencyFlowById(Long id) {
        return virtualCurrencyFlowMapper.selectById(id);
    }

    @Override
    public void addVirtualCurrencyFlow(VirtualCurrencyFlow virtualCurrencyFlow) {
        virtualCurrencyFlowMapper.insert(virtualCurrencyFlow);
    }
}