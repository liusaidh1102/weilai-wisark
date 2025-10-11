package com.weilai.model.topic.dos;


import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文章标签关联表
 * @TableName article_tag
 */
@Data
@TableName("article_tag")
@Schema(description = "文章标签关联表")
public class ArticleTag implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Schema(description = "ID")
    private Long id;

    /**
     * 文章ID
     */
    @NotNull(message = "[文章ID]不能为空")
    @Schema(description = "文章ID")
    private Long articleId;

    /**
     * 标签ID
     */
    @NotNull(message = "[标签ID]不能为空")
    @Schema(description = "标签ID")
    private Long tagId;

    /**
     * 创建时间
     */
    @NotNull(message = "[创建时间]不能为空")
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;
}