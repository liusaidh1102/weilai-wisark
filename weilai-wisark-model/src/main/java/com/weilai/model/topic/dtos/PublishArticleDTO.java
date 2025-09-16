package com.weilai.model.topic.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PublishArticleDTO {
    // 文章ID
    private Long id;
    // 标题
    @NotNull(message = "[标题]不能为空")
    @Size(min = 1, max = 50, message = "标题长度在1-50个字符之间")
    private String title;
    //文章内容
    @NotNull(message = "[内容]不能为空")
    @Size(min = 1, message = "文章内容不能为空")
    private String content;
    // 文章封面图片
    @Schema(description = "文章封面图片")
    private String coverImage;
    // 文章类型 1-长文 2-沸点 3-翻译文章
    @NotNull(message = "[文章类型]不能为空")
    @Schema(description = "文章类型：1-长文，2-沸点（短内容），3-翻译文章")
    private Integer type;
    // 是否原创 0-转载 1-原创
    @NotNull(message = "[是否原创]不能为空")
    @Schema(description = "是否原创：0-转载，1-原创")
    private Integer isOriginal;
    // 转载来源URL
    @Schema(description = "转载来源URL（非原创时必填）")
    private String originalUrl;
    //文章的标签
    @NotNull(message = "[标签ID]不能为空")
    @Schema(description = "文章的标签ID")
    private String tagId;
    //文章的分类
    @NotBlank
    @NotNull(message = "[分类ID]不能为空")
    @Schema(description = "文章的分类ID")
    private String categoryId;
}
