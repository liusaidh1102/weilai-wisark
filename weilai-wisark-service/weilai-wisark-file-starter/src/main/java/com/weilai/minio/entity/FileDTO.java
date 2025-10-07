package com.weilai.minio.entity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class FileDTO {
    /**
     * 原始文件名
     */
    @NotBlank
    private String originalName;
    /**
     * md5
     */
    @NotBlank
    private String md5;
    /**
     * 分片大小
     */
    @Min(value = 1, message = "分片大小不能小于1")
    @NotNull
    private Integer chunkSize;
    /**
     * 分片总数
     */
    @Min(value = 1, message = "分片总数不能小于1")
    @NotNull
    private Integer chunkNum;
    /**
     * 文件的contentType   image/png等,控制文件以何种方式显示
     */
    private String contentType;
//    /**
//     * 已上传的分片索引
//     */
//    private List<Integer> uploadedChunks;
}