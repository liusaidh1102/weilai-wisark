package com.weilai.model.streaming.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * 直播预约表
 * @TableName live_reservation
 */
@Data
@TableName("live_reservation")
@Schema(description = "直播预约表")
public class LiveReservation implements Serializable {

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
     * 提醒方式：1-系统通知，2-邮件，3-短信
     */
    @NotNull(message = "[提醒方式]不能为空")
    @Schema(description = "提醒方式：1-系统通知，2-邮件，3-短信")
    private Integer remindType;

    /**
     * 状态：0-已取消，1-有效
     */
    @NotNull(message = "[状态]不能为空")
    @Schema(description = "状态：0-已取消，1-有效")
    private Integer status;

    /**
     * 创建时间
     */
    @NotNull(message = "[创建时间]不能为空")
    @Schema(description = "创建时间")
    private DateTime createdAt;

    /**
     * 更新时间
     */
    @NotNull(message = "[更新时间]不能为空")
    @Schema(description = "更新时间")
    private DateTime updatedAt;
}