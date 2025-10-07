package com.weilai.common.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

/**
 * 分页请求参数类
 */
@Data
@Schema(description = "分页请求参数")
public class PageRequest {

    @Min(value = 1, message = "当前页必须大于0")
    @Schema(description = "当前页", example = "1")
    private Long pageNum = 1L;

    @Min(value = 1, message = "每页大小必须大于0")
    @Schema(description = "每页显示条数", example = "10")
    private Long pageSize = 10L;

    @Schema(description = "排序字段集合")
    @Size(max = 10, message = "排序字段最多10")
    private List<SortItem> sortItemList;


    /**
     * 排序DTO类
     */
    @Data
    public static class SortItem {

        @Schema(description = "true正序|false倒序")
        @NotNull(message = "排序规则不能为空")
        private Boolean isAsc;

        @Schema(description = "排序字段")
        @NotBlank(message = "排序字段不能为空")
        private String column;

    }


}
