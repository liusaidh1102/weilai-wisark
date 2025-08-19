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
 * 虚拟货币流水表
 * @TableName virtual_currency_flow
 */
@Data
@TableName("virtual_currency_flow")
@Schema(description = "虚拟货币流水表")
public class VirtualCurrencyFlow implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @NotNull(message = "[ID]不能为空")
    @Schema(description = "ID")
    private Long id;

    /**
     * 用户ID
     */
    @NotNull(message = "[用户ID]不能为空")
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 金额（正数为充值，负数为消费）
     */
    @NotNull(message = "[金额]不能为空")
    @Schema(description = "金额（正数为充值，负数为消费）")
    private BigDecimal amount;

    /**
     * 类型：1-充值，2-礼物打赏，3-退款
     */
    @NotNull(message = "[类型]不能为空")
    @Schema(description = "类型：1-充值，2-礼物打赏，3-退款")
    private Integer type;

    /**
     * 关联ID（如打赏时关联live_reward.id）
     */
    @Schema(description = "关联ID（如打赏时关联live_reward.id）")
    private Long relatedId;

    /**
     * 操作后余额
     */
    @NotNull(message = "[操作后余额]不能为空")
    @Schema(description = "操作后余额")
    private BigDecimal balanceAfter;

    /**
     * 创建时间
     */
    @NotNull(message = "[创建时间]不能为空")
    @Schema(description = "创建时间")
    private DateTime createdAt;
}