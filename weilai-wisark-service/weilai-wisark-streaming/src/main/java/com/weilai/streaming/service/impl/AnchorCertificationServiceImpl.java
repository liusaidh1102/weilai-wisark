package com.weilai.streaming.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.model.streaming.dos.AnchorCertification;
import com.weilai.streaming.mapper.AnchorCertificationMapper;
import com.weilai.streaming.service.AnchorCertificationService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 主播认证服务实现类
 */
@Service
public class AnchorCertificationServiceImpl extends ServiceImpl<AnchorCertificationMapper, AnchorCertification> implements AnchorCertificationService {

    @Resource
    private AnchorCertificationMapper anchorCertificationMapper;

    @Override
    public List<AnchorCertification> getAllAnchorCertifications() {
        QueryWrapper<AnchorCertification> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created_at");
        return anchorCertificationMapper.selectList(queryWrapper);
    }

    @Override
    public AnchorCertification getAnchorCertificationById(Long id) {
        return anchorCertificationMapper.selectById(id);
    }

    @Override
    public void addAnchorCertification(AnchorCertification anchorCertification) {
        anchorCertificationMapper.insert(anchorCertification);
    }

    @Override
    public void updateAnchorCertification(AnchorCertification anchorCertification) {
        anchorCertification.setUpdatedAt(new DateTime());
        anchorCertificationMapper.updateById(anchorCertification);
    }

    @Override
    public void deleteAnchorCertification(Long id) {
        anchorCertificationMapper.deleteById(id);
    }
}