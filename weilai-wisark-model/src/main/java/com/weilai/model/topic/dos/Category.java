package com.weilai.model.topic.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@TableName("category")
@Schema(description = "文章分类表")
public class Category {
    @Schema(description = "ID")
    @NotNull(message = "[ID]不能为空")
    private Long id;
    @Schema(description = "分类名称")
    @NotNull(message = "[分类名称]不能为空")
    private String name;
    @Schema(description = "排序权重")
    @NotNull(message = "[排序权重]不能为空")
    private Integer sort;
    @Schema(description = "创建时间")
    @NotNull(message = "[创建时间]不能为空")
    private DateTime createdAt;
    @Schema(description = "更新时间")
    @NotNull(message = "[更新时间]不能为空")
    private DateTime updatedAt;
    @Schema(description = "删除时间")
    private DateTime deletedAt;
}
