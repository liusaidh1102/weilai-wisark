package com.weilai.model.topic.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryDTO {
    /**
     * ID
     */
    private Long id;
    /**
     * 分类名称
     */
    @NotNull(message = "[分类名称]不能为空")
    private String name;
    /**
     * 排序权重
     */
    private Integer sort;

}
