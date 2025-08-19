package com.weilai.model.user.dos;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
/**
* 角色与权限的关联表
* @TableName tb_sys_role_permission
*/
@TableName("tb_sys_role_permission")
@Data
public class SysRolePermission implements Serializable {

    /**
    * 关联ID
    */
    @NotNull(message="[关联ID]不能为空")
    private Long id;
    /**
    * 角色ID（关联sys_role.id）
    */
    @NotNull(message="[角色ID（关联sys_role.id）]不能为空")
    private Long roleId;
    /**
    * 权限ID（关联sys_permission.id）
    */
    @NotNull(message="[权限ID（关联sys_permission.id）]不能为空")
    private Long permissionId;
    /**
    * 权限过期时间（NULL-永久有效）
    */
    private Date expireTime;
    /**
    *
    */
    @NotNull(message="[]不能为空")
    private Date createTime;

}
