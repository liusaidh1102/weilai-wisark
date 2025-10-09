package com.weilai.model.streaming.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * 直播统计明细表
 * @TableName live_statistics
 */
@Data
@TableName("live_statistics")
@Schema(description = "直播统计明细表")
public class LiveStatistics implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @NotNull(message = "[ID]不能为空")
    @Schema(description = "ID")
    private Long id;

    /**
     * 直播ID
     */
    @NotNull(message = "[直播ID]不能为空")
    @Schema(description = "直播ID")
    private Long liveId;

    /**
     * 最高并发观看数
     */
    @NotNull(message = "[最高并发观看数]不能为空")
    @Schema(description = "最高并发观看数")
    private Integer concurrentViewers;

    /**
     * 并发峰值时间
     */
    @Schema(description = "并发峰值时间")
    private DateTime peakTime;

    /**
     * 总消息数
     */
    @NotNull(message = "[总消息数]不能为空")
    @Schema(description = "总消息数")
    private Integer messageCount;

    /**
     * 新增关注数（若有关注功能）
     */
    @NotNull(message = "[新增关注数]不能为空")
    @Schema(description = "新增关注数（若有关注功能）")
    private Integer newFollowerCount;

    /**
     * 创建时间
     */
    @NotNull(message = "[创建时间]不能为空")
    @Schema(description = "创建时间")
    private DateTime createdAt;
}