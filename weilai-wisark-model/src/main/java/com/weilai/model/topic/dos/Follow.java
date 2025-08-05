package com.weilai.model.topic.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * 关注记录表
 * @TableName follow
 */
@Data
@TableName("follow")
@Schema(description = "关注记录表")
public class Follow implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @NotNull(message = "[ID]不能为空")
    @Schema(description = "ID")
    private Long id;

    /**
     * 关注者ID
     */
    @NotNull(message = "[关注者ID]不能为空")
    @Schema(description = "关注者ID")
    private Long userId;

    /**
     * 目标类型：1-用户，2-标签
     */
    @NotNull(message = "[目标类型]不能为空")
    @Schema(description = "目标类型：1-用户，2-标签")
    private Integer targetType;

    /**
     * 目标ID
     */
    @NotNull(message = "[目标ID]不能为空")
    @Schema(description = "目标ID")
    private Long targetId;

    /**
     * 创建时间
     */
    @NotNull(message = "[创建时间]不能为空")
    @Schema(description = "创建时间")
    private DateTime createdAt;

    /**
     * 取消关注时间
     */
    @Schema(description = "取消关注时间")
    private DateTime deletedAt;
}