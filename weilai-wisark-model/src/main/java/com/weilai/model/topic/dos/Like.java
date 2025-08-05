package com.weilai.model.topic.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * 点赞记录表
 * @TableName like
 */
@Data
@TableName("like")
@Schema(description = "点赞记录表")
public class Like implements Serializable {

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
     * 目标类型：1-文章，2-评论
     */
    @NotNull(message = "[目标类型]不能为空")
    @Schema(description = "目标类型：1-文章，2-评论")
    private Integer targetType;

    /**
     * 目标ID（对应类型的ID）
     */
    @NotNull(message = "[目标ID]不能为空")
    @Schema(description = "目标ID（对应类型的ID）")
    private Long targetId;

    /**
     * 创建时间
     */
    @NotNull(message = "[创建时间]不能为空")
    @Schema(description = "创建时间")
    private DateTime createdAt;

    /**
     * 取消点赞时间（软删除）
     */
    @Schema(description = "取消点赞时间（软删除）")
    private DateTime deletedAt;
}