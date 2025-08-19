package com.weilai.streaming.service;

import com.weilai.model.streaming.dos.VirtualCurrencyFlow;

import java.util.List;

/**
 * 虚拟货币流水服务接口
 */
public interface VirtualCurrencyFlowService {

    /**
     * 获取所有虚拟货币流水
     *
     * @return 虚拟货币流水列表
     */
    List<VirtualCurrencyFlow> getAllVirtualCurrencyFlows();

    /**
     * 根据ID获取虚拟货币流水
     *
     * @param id 虚拟货币流水ID
     * @return 虚拟货币流水详情
     */
    VirtualCurrencyFlow getVirtualCurrencyFlowById(Long id);

    /**
     * 添加虚拟货币流水
     *
     * @param virtualCurrencyFlow 虚拟货币流水信息
     */
    void addVirtualCurrencyFlow(VirtualCurrencyFlow virtualCurrencyFlow);
}