package com.weilai.model.topic.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * 文章收藏表
 * @TableName collection
 */
@Data
@TableName("collection")
@Schema(description = "文章收藏表")
public class Collection implements Serializable {

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
     * 收藏文件夹ID
     */
    @NotNull(message = "[收藏文件夹ID]不能为空")
    @Schema(description = "收藏文件夹ID")
    private Long folderId;

    /**
     * 收藏备注（可选）
     */
    @Schema(description = "收藏备注（可选）")
    private String note;

    /**
     * 创建时间
     */
    @NotNull(message = "[创建时间]不能为空")
    @Schema(description = "创建时间")
    private DateTime createdAt;

    /**
     * 更新时间
     */
    @NotNull(message = "[更新时间]不能为空")
    @Schema(description = "更新时间")
    private DateTime updatedAt;

    /**
     * 取消收藏时间
     */
    @Schema(description = "取消收藏时间")
    private DateTime deletedAt;
}