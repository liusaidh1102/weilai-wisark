package com.weilai.model.user.dos;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
* 用户与角色的关联表
* @TableName tb_sys_user_role
*/
@TableName("tb_sys_user_role")
@Data
public class SysUserRole implements Serializable {
    /**
    * 关联ID
    */
    @NotNull(message="[关联ID]不能为空")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
    * 用户ID（关联tb_user.id）
    */
    @NotNull(message="[用户ID（关联tb_user.id）]不能为空")
    private Long userId;
    /**
    * 角色ID（关联sys_role.id）
    */
    @NotNull(message="[角色ID（关联sys_role.id）]不能为空")
    private Long roleId;
    /**
    * 角色过期时间（NULL-永久有效）
    */
    private Date expireTime;
    /**
    * 分配者ID（关联tb_user.id）
    */
    @NotNull(message="[分配者ID（关联tb_user.id）]不能为空")
    private Long createUserId;
    /**
    *
    */
    @NotNull(message="[]不能为空")
    private Date createTime;

}
