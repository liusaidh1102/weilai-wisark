package com.weilai.model.streaming.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * 直播观看记录表
 * @TableName live_watch_record
 */
@Data
@TableName("live_watch_record")
@Schema(description = "直播观看记录表")
public class LiveWatchRecord implements Serializable {

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
     * 直播ID
     */
    @NotNull(message = "[直播ID]不能为空")
    @Schema(description = "直播ID")
    private Long liveId;

    /**
     * 进入时间
     */
    @NotNull(message = "[进入时间]不能为空")
    @Schema(description = "进入时间")
    private DateTime enterTime;

    /**
     * 离开时间
     */
    @Schema(description = "离开时间")
    private DateTime leaveTime;

    /**
     * 观看时长(秒)
     */
    @Schema(description = "观看时长(秒)")
    private Integer watchDuration;

    /**
     * 是否完整观看：0-否，1-是
     */
    @NotNull(message = "[是否完整观看]不能为空")
    @Schema(description = "是否完整观看：0-否，1-是")
    private Integer isComplete;
}