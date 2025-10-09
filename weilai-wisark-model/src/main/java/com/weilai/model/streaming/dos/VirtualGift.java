package com.weilai.model.streaming.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 虚拟礼物表
 * @TableName virtual_gift
 */
@Data
@TableName("virtual_gift")
@Schema(description = "虚拟礼物表")
public class VirtualGift implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @NotNull(message = "[ID]不能为空")
    @Schema(description = "ID")
    private Long id;

    /**
     * 礼物名称
     */
    @NotNull(message = "[礼物名称]不能为空")
    @Schema(description = "礼物名称")
    private String name;

    /**
     * 单价（虚拟币）
     */
    @NotNull(message = "[单价]不能为空")
    @Schema(description = "单价（虚拟币）")
    private BigDecimal price;

    /**
     * 礼物图标
     */
    @Schema(description = "礼物图标")
    private String imageUrl;

    /**
     * 是否有特效：0-否，1-是
     */
    @NotNull(message = "[是否有特效]不能为空")
    @Schema(description = "是否有特效：0-否，1-是")
    private Integer isEffect;

    /**
     * 特效资源URL（is_effect=1时必填）
     */
    @Schema(description = "特效资源URL（is_effect=1时必填）")
    private String effectUrl;

    /**
     * 排序
     */
    @NotNull(message = "[排序]不能为空")
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 状态：0-下架，1-上架
     */
    @NotNull(message = "[状态]不能为空")
    @Schema(description = "状态：0-下架，1-上架")
    private Integer status;

    /**
     * 创建时间
     */
    @NotNull(message = "[创建时间]不能为空")
    @Schema(description = "创建时间")
    private DateTime createdAt;
}