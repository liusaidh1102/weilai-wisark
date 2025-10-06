package com.weilai.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
@Schema(description = "分页结果")
public class PageResult<T> implements Serializable {

    @Schema(description = "数据列表")
    private List<T> records = Collections.emptyList();

    @Schema(description = "当前页")
    private long current;

    @Schema(description = "每页显示条数")
    private long size;

    @Schema(description = "总记录数")
    private long total;

    @Schema(description = "总页数")
    private long pages;

    public PageResult() {
    }

    public PageResult(long current, long size) {
        this.current = current;
        this.size = size;
    }

    public PageResult(List<T> records, long current, long size, long total, long pages) {
        this.records = records;
        this.current = current;
        this.size = size;
        this.total = total;
        this.pages = pages;
    }
}
