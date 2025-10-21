package com.weilai.streaming.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.common.response.Result;
import com.weilai.model.streaming.dos.DanMu;
import com.weilai.streaming.mapper.DanMuMapper;
import com.weilai.streaming.service.DanMuService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DanMuServiceImpl extends ServiceImpl<DanMuMapper, DanMu> implements DanMuService {
    @Resource
    private DanMuMapper danMuMapper;
    private  static final Map<String,List<DanMu>> danMuMaps=new ConcurrentHashMap<>();
    @Override
    public Result<String> addDanMu(DanMu danMu) {
        return danMuMapper.insert(danMu) > 0 ? Result.ok("添加成功") : Result.fail("添加失败");
    }

    @Override
    public Result<List<DanMu>> getDanMuList(String roomId) {
        QueryWrapper<DanMu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("room_id", roomId).orderByAsc("on_time");
        List<DanMu> danMus = danMuMapper.selectList(queryWrapper);
        return Result.ok(danMus);
    }

}
