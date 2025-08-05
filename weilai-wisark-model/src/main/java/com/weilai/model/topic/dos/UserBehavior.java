package com.weilai.model.topic.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * 用户行为追踪表（用于推荐）
 * @TableName user_behavior
 */
@Data
@TableName("user_behavior")
@Schema(description = "用户行为追踪表（用于推荐）")
public class UserBehavior implements Serializable {

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
     * 文章ID
     */
    @NotNull(message = "[文章ID]不能为空")
    @Schema(description = "文章ID")
    private Long articleId;

    /**
     * 行为类型：1-浏览（>30s记为有效），2-点赞，3-收藏，4-分享，5-举报
     */
    @NotNull(message = "[行为类型]不能为空")
    @Schema(description = "行为类型：1-浏览（>30s记为有效），2-点赞，3-收藏，4-分享，5-举报")
    private Integer behaviorType;

    /**
     * 浏览时长（秒，仅类型1有效）
     */
    @Schema(description = "浏览时长（秒，仅类型1有效）")
    private Integer duration;

    /**
     * 创建时间
     */
    @NotNull(message = "[创建时间]不能为空")
    @Schema(description = "创建时间")
    private DateTime createdAt;
}