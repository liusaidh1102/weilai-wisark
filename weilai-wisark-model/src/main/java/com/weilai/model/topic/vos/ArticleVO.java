package com.weilai.model.topic.vos;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ArticleVO {
    // 文章ID
    private Long id;
    // 标题
    @Schema(description = "标题（沸点可短至10字）")
    private String title;
    //文章内容
    @Schema(description = "文章内容（支持Markdown/富文本）")
    private String content;
    // 文章封面图片
    private String coverImage;
    // 文章类型 1-长文 2-沸点 3-翻译文章
    @Schema(description = "文章类型：1-长文，2-沸点（短内容），3-翻译文章", allowableValues = {"1", "2", "3"})
    private Integer type;
    // 是否原创 0-转载 1-原创
    @Schema(description = "是否原创：0-转载，1-原创", allowableValues = {"0", "1"})
    private Integer isOriginal;
    // 转载来源URL
    @Schema(description = "转载来源URL（非原创时必填）")
    private String originalUrl;
    //文章的标签
    @Schema(description = "文章的标签")
    private String tagName;
    //文章的分类
    @Schema(description = "文章的分类")
    private String categoryName;
}
