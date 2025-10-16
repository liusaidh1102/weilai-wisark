package com.weilai.streaming.service;

import com.weilai.common.response.Result;
import com.weilai.model.streaming.dos.DanMu;

import java.util.List;

/**
 * 弹幕服务接口
 */
public interface DanMuService{
    /**
     * 添加弹幕
     * @param danMu
     * @return
     */
    Result<String> addDanMu(DanMu danMu);

    /**
     * 获取弹幕列表
     * @param roomId
     * @return
     */
    Result<List<DanMu>> getDanMuList(String roomId);
}
