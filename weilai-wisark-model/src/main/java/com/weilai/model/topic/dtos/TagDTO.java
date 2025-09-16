package com.weilai.model.topic.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TagDTO {
    /**
     * ID
     */
    private Long id;
    /**
     * 分类名称
     */
    @NotNull(message = "[标签名称]不能为空")
    private String name;
    /**
     * 排序权重
     */
    private Integer sort;
    /**
     * 父标签ID，顶级标签为NULL
     */
    private Long parentId;
}
