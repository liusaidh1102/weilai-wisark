package com.weilai.model.streaming.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * 直播-标签关联表
 * @TableName live_tag
 */
@Data
@TableName("live_tag")
@Schema(description = "直播-标签关联表")
public class LiveTag implements Serializable {

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
     * 标签ID（关联之前的tag表）
     */
    @NotNull(message = "[标签ID]不能为空")
    @Schema(description = "标签ID（关联之前的tag表）")
    private Long tagId;

    /**
     * 创建时间
     */
    @NotNull(message = "[创建时间]不能为空")
    @Schema(description = "创建时间")
    private DateTime createdAt;
}