package com.weilai.model.topic.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * 文章评论表（支持多级）
 * @TableName comment
 */
@Data
@TableName("comment")
@Schema(description = "文章评论表（支持多级）")
public class Comment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 评论ID
     */
    @NotNull(message = "[评论ID]不能为空")
    @Schema(description = "评论ID")
    private Long id;

    /**
     * 所属文章ID
     */
    @NotNull(message = "[所属文章ID]不能为空")
    @Schema(description = "所属文章ID")
    private Long articleId;

    /**
     * 评论者ID
     */
    @NotNull(message = "[评论者ID]不能为空")
    @Schema(description = "评论者ID")
    private Long userId;

    /**
     * 父评论ID（顶级评论为NULL）
     */
    @Schema(description = "父评论ID（顶级评论为NULL）")
    private Long parentId;

    /**
     * 被@用户ID
     */
    @Schema(description = "被@用户ID")
    private Long atUserId;

    /**
     * 评论内容
     */
    @NotBlank(message = "[评论内容]不能为空")
    @Schema(description = "评论内容")
    private String content;

    /**
     * 点赞数
     */
    @NotNull(message = "[点赞数]不能为空")
    @Schema(description = "点赞数")
    private Integer likeCount;

    /**
     * 是否被作者精选：0-否，1-是
     */
    @NotNull(message = "[是否被作者精选]不能为空")
    @Schema(description = "是否被作者精选：0-否，1-是")
    private Integer isSelection;

    /**
     * 是否热门（点赞数阈值）：0-否，1-是
     */
    @NotNull(message = "[是否热门]不能为空")
    @Schema(description = "是否热门（点赞数阈值）：0-否，1-是")
    private Integer isHot;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private DateTime createdAt;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private DateTime updatedAt;

    /**
     * 软删除时间
     */
    @Schema(description = "软删除时间")
    private DateTime deletedAt;
}