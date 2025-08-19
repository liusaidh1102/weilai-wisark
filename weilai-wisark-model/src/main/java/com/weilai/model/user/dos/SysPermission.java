package com.weilai.model.user.dos;
import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
* 系统权限表（所有可操作权限点）
* @TableName tb_sys_permission
*/
@Data
@TableName("tb_sys_permission")
public class SysPermission implements Serializable {

    /**
    * 权限ID
    */
    @NotNull(message="[权限ID]不能为空")
    private Long id;
    /**
    * 权限编码（唯一标识，如"resource:upload"）
    */
    @NotBlank(message="[权限编码（唯一标识，如resource:upload）]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @Length(max= 100,message="编码长度不能超过100")
    private String permissionCode;
    /**
    * 权限名称（如"资源上传"）
    */
    @NotBlank(message="[权限名称（如资源上传）]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @Length(max= 100,message="编码长度不能超过100")
    private String permissionName;
    /**
    * 权限类型（1-菜单，2-按钮，3-接口，4-数据权限）
    */
    @NotNull(message="[权限类型（1-菜单，2-按钮，3-接口，4-数据权限）]不能为空")
    private Integer permissionType;
    /**
    * 父权限ID（0-顶级权限，如"资源管理"是"资源上传"的父权限）
    */
    private Long parentId;
    /**
    * 关联资源路径（菜单URL/接口路径，如"/api/resource/upload"）
    */
    @Size(max= 255,message="编码长度不能超过255")
    @Length(max= 255,message="编码长度不能超过255")
    private String resourcePath;
    /**
    * 排序权重（数字越大越靠前）
    */
    private Integer sort;
    /**
    * 是否启用（1-是，0-否）
    */
    @NotNull(message="[是否启用（1-是，0-否）]不能为空")
    private Integer isEnabled;
    /**
    * 权限描述
    */
    @Size(max= 500,message="编码长度不能超过500")
    @Length(max= 500,message="编码长度不能超过500")
    private String remark;
    /**
    *
    */
    @NotNull(message="[]不能为空")
    private Date createTime;
    /**
    *
    */
    @NotNull(message="[]不能为空")
    private Date updateTime;

}
