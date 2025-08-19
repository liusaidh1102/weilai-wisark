package com.weilai.streaming.service;

import com.weilai.model.streaming.dos.AnchorCertification;

import java.util.List;

/**
 * 主播认证服务接口
 */
public interface AnchorCertificationService {

    /**
     * 获取所有主播认证信息
     *
     * @return 主播认证信息列表
     */
    List<AnchorCertification> getAllAnchorCertifications();

    /**
     * 根据ID获取主播认证信息
     *
     * @param id 主播认证ID
     * @return 主播认证信息详情
     */
    AnchorCertification getAnchorCertificationById(Long id);

    /**
     * 添加主播认证信息
     *
     * @param anchorCertification 主播认证信息
     */
    void addAnchorCertification(AnchorCertification anchorCertification);

    /**
     * 更新主播认证信息
     *
     * @param anchorCertification 主播认证信息
     */
    void updateAnchorCertification(AnchorCertification anchorCertification);

    /**
     * 删除主播认证信息
     *
     * @param id 主播认证ID
     */
    void deleteAnchorCertification(Long id);
}