package com.weilai.model.topic.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * 技术领域标签表
 * @TableName tag
 */
@Data
@TableName("tag")
@Schema(description = "技术领域标签表")
public class TagTopic implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标签ID
     */
    @NotNull(message = "[标签ID]不能为空")
    @Schema(description = "标签ID")
    private Long id;

    /**
     * 标签名称
     */
    @NotNull(message = "[标签名称]不能为空")
    @Schema(description = "标签名称")
    private String name;

    /**
     * 父标签ID，顶级标签为NULL
     */
    @Schema(description = "父标签ID，顶级标签为NULL")
    private Long parentId;

    /**
     * 排序权重
     */
    @NotNull(message = "[排序权重]不能为空")
    @Schema(description = "排序权重")
    private Integer sort;

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
     * 删除时间（软删除）
     */
    @Schema(description = "删除时间（软删除）")
    private DateTime deletedAt;
}