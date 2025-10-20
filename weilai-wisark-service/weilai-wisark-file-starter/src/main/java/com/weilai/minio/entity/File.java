package com.weilai.minio.entity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
* OSS对象存储表
* @TableName tb_file
*/
@Data
@TableName("tb_file")
@AllArgsConstructor
@NoArgsConstructor
public class File implements Serializable {

    /**
    * 对象存储主键自增
    */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
    * 文件名
    */
    private String fileName;
    /**
    * 原名
    */
    private String originalName;
    /**
     * md5
     */
    private String md5;
    /**
     * 分片大小
     */
    private Integer chunkSize;
    /**
     * 分片总数
     */
    private Integer chunkNum;
    /**
     * uploadId
     */
    private String uploadId;
    /**
     * bucket
     */
    private String bucket;
    /**
     * 文件mime类型
     */
    private String contentType;
    /**
    * 文件后缀名
    */
    private String fileSuffix;
    /**
    * URL地址
    */
    private String url;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 服务商(默认是minio)
    */
    private String service;

    /**
     * 已上传的分片索引
     */
    @TableField(exist = false)
    private List<Integer> uploadedChunks;

    public File(String fileName, String originalName, String fileSuffix, String url) {
        this.fileName = fileName;
        this.originalName = originalName;
        this.fileSuffix = fileSuffix;
        this.url = url;
    }
}
