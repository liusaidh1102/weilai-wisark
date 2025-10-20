package com.weilai.model.topic.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文章/沸点表
 * @TableName article
 */
@Data
@TableName("article")
@Schema(description = "文章/沸点表")
public class Article implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 文章ID
     */
    @NotNull(message = "[文章ID]不能为空")
    @Schema(description = "文章ID")
    private Long id;

    /**
     * 作者ID
     */
    @NotNull(message = "[作者ID]不能为空")
    @Schema(description = "作者ID")
    private Long userId;

    /**
     * 标题（沸点可短至10字）
     */
    @NotNull(message = "[标题]不能为空")
    @Schema(description = "标题（沸点可短至10字）")
    private String title;

    /**
     * 内容（支持Markdown/富文本）
     */
    @NotNull(message = "[内容]不能为空")
    @Schema(description = "内容（支持Markdown/富文本）")
    private String content;

    /**
     * 摘要（列表页展示）
     */
    @Schema(description = "摘要（列表页展示）")
    private String summary;

    /**
     * 封面图（可选）
     */
    @Schema(description = "封面图（可选）")
    private String coverImage;

    /**
     * 类型：1-长文，2-沸点（短内容），3-翻译文章
     */
    @NotNull(message = "[类型]不能为空")
    @Schema(description = "类型：1-长文，2-沸点（短内容），3-翻译文章")
    private Integer type;

    /**
     * 状态：0-草稿，1-待审核，2-已发布，3-审核失败，4-下架
     */
    @NotNull(message = "[状态]不能为空")
    @Schema(description = "状态：0-草稿，1-待审核，2-已发布，3-审核失败，4-下架")
    private Integer status;

    /**
     * 浏览量（去重）
     */
    @NotNull(message = "[浏览量]不能为空")
    @Schema(description = "浏览量（去重）")
    private Integer viewCount;

    /**
     * 点赞数
     */
    @NotNull(message = "[点赞数]不能为空")
    @Schema(description = "点赞数")
    private Integer likeCount;

    /**
     * 收藏数
     */
    @NotNull(message = "[收藏数]不能为空")
    @Schema(description = "收藏数")
    private Integer collectCount;

    /**
     * 评论数
     */
    @NotNull(message = "[评论数]不能为空")
    @Schema(description = "评论数")
    private Integer commentCount;

    /**
     * 分享数
     */
    @NotNull(message = "[分享数]不能为空")
    @Schema(description = "分享数")
    private Integer shareCount;

    /**
     * 是否原创：0-转载，1-原创
     */
    @NotNull(message = "[是否原创]不能为空")
    @Schema(description = "是否原创：0-转载，1-原创")
    private Integer isOriginal;

    /**
     * 转载来源URL（非原创时必填）
     */
    @Schema(description = "转载来源URL（非原创时必填）")
    private String originalUrl;

    /**
     * 发布时间
     */
    @Schema(description = "发布时间")
    private LocalDateTime publishedAt;

    /**
     * 创建时间
     */
    @NotNull(message = "[创建时间]不能为空")
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @NotNull(message = "[更新时间]不能为空")
    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;

    /**
     * 删除时间
     */
    @Schema(description = "删除时间")
    private LocalDateTime deletedAt;
}