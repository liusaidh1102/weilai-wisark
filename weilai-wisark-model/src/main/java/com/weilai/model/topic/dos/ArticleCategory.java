package com.weilai.model.topic.dos;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("article_category")
@Schema(description = "文章分类关联表")
public class ArticleCategory {
    @Schema(description = "ID")
    private Long id;
    @Schema(description = "文章ID")
    private Long articleId;
    @Schema(description = "分类ID")
    private Long categoryId;
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
}
