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
 * 直播打赏记录表
 * @TableName live_reward
 */
@Data
@TableName("live_reward")
@Schema(description = "直播打赏记录表")
public class LiveReward implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @NotNull(message = "[ID]不能为空")
    @Schema(description = "ID")
    private Long id;

    /**
     * 打赏用户
     */
    @NotNull(message = "[打赏用户]不能为空")
    @Schema(description = "打赏用户")
    private Long userId;

    /**
     * 关联直播
     */
    @NotNull(message = "[关联直播]不能为空")
    @Schema(description = "关联直播")
    private Long liveId;

    /**
     * 打赏金额
     */
    @NotNull(message = "[打赏金额]不能为空")
    @Schema(description = "打赏金额")
    private BigDecimal amount;

    /**
     * 类型：1-礼物打赏，2-直接打赏
     */
    @NotNull(message = "[类型]不能为空")
    @Schema(description = "类型：1-礼物打赏，2-直接打赏")
    private Integer rewardType;

    /**
     * 支付流水号
     */
    @Schema(description = "支付流水号")
    private String transactionId;

    /**
     * 创建时间
     */
    @NotNull(message = "[创建时间]不能为空")
    @Schema(description = "创建时间")
    private DateTime createdAt;
}