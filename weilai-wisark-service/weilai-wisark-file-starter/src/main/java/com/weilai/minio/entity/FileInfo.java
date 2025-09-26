package com.weilai.minio.entity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
/**
* OSS对象存储表
* @TableName tb_file
*/
@Data
@TableName("tb_file")
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo implements Serializable {

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
    * 上传人
    */
    private Long createBy;
    /**
    * 更新时间
    */
    private Date updateTime;
    /**
    * 更新人
    */
    private Long updateBy;
    /**
    * 服务商(默认是minio)
    */
    private String service;

    public FileInfo(String fileName, String originalName, String fileSuffix, String url, Long createBy, Long updateBy) {
        this.fileName = fileName;
        this.originalName = originalName;
        this.fileSuffix = fileSuffix;
        this.url = url;
        this.createBy = createBy;
        this.updateBy = updateBy;
    }
}
