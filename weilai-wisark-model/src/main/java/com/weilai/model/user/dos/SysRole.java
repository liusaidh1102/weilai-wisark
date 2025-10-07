package com.weilai.model.user.dos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
/**
* 系统角色表，用于管理用户角色及权限等级
* @TableName tb_sys_role
*/
@Data
@TableName("tb_sys_role")
public class SysRole implements Serializable {

    /**
    * 角色唯一标识
    */
    @NotNull(message="[角色唯一标识]不能为空")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer roleId;
    /**
    * 角色名称（如管理员、普通用户）
    */
    @NotBlank(message="[角色名称（如管理员、普通用户）]不能为空")
    @Size(max= 50,message="编码长度不能超过50")
    @Length(max= 50,message="编码长度不能超过50")
    private String roleName;
    /**
    * 角色功能描述
    */
    @Size(max= -1,message="编码长度不能超过-1")
    @Length(max= -1,message="编码长度不能超过-1")
    private String roleDesc;
    /**
    * 角色等级（1-10，数值越大权限越高）
    */
    @NotNull(message="[角色等级（1-10，数值越大权限越高）]不能为空")
    private Integer roleLevel;
    /**
    * 创建时间
    */
    @NotNull(message="[创建时间]不能为空")
    private Date createTime;
    /**
    * 最后更新时间
    */
    @NotNull(message="[最后更新时间]不能为空")
    private Date updateTime;
    /**
    * 是否启用（1=启用，0=禁用）
    */
    @NotNull(message="[是否启用（1=启用，0=禁用）]不能为空")
    private Integer isEnabled;

}
