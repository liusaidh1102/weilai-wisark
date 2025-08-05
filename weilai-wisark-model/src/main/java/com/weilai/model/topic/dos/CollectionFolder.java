package com.weilai.model.topic.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * 收藏文件夹表
 * @TableName collection_folder
 */
@Data
@TableName("collection_folder")
@Schema(description = "收藏文件夹表")
public class CollectionFolder implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @NotNull(message = "[ID]不能为空")
    @Schema(description = "ID")
    private Long id;

    /**
     * 所属用户ID
     */
    @NotNull(message = "[所属用户ID]不能为空")
    @Schema(description = "所属用户ID")
    private Long userId;

    /**
     * 文件夹名称
     */
    @NotNull(message = "[文件夹名称]不能为空")
    @Schema(description = "文件夹名称")
    private String name;

    /**
     * 是否默认文件夹：0-否，1-是
     */
    @NotNull(message = "[是否默认文件夹]不能为空")
    @Schema(description = "是否默认文件夹：0-否，1-是")
    private Integer isDefault;

    /**
     * 包含文章数
     */
    @NotNull(message = "[包含文章数]不能为空")
    @Schema(description = "包含文章数")
    private Integer articleCount;

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
     * 删除时间
     */
    @Schema(description = "删除时间")
    private DateTime deletedAt;
}