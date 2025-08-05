package com.weilai.model.user.dos;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
/**
* 用户基础信息表
* @TableName tb_user
*/
@Data
@TableName("tb_user")
@Schema(description = "用户基础信息表")
public class User implements Serializable {

    /*
    * 序列化版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
    * 用户唯一ID（雪花算法生成）
    */
    @NotNull(message = "[用户唯一ID（雪花算法生成）]不能为空")
    @Schema(description = "用户唯一ID（雪花算法生成）")
    private Long id;
    /**
    * 加密后的密码（如BCrypt加密，可以为空，最开始通过第三方登录）
    */
    @Size(max= 100,message="编码长度不能超过100")
    @Schema(description = "加密后的密码（如BCrypt加密，可以为空，最开始通过第三方登录）")
    @Length(max= 100,message="编码长度不能超过100")
    private String password;
    /**
    * 用户昵称（第三方登录时同步，否则走默认，必填）
    */
    @NotBlank(message="[用户昵称（第三方登录时同步，否则走默认，必填）]不能为空")
    @Size(max= 100,message="编码长度不能超过100")
    @Schema(description = "用户昵称（第三方登录时同步，否则走默认，必填）")
    @Length(max= 100,message="编码长度不能超过100")
    private String nickname;
    /**
    * 头像URL，第三方登录同步，否则走默认
    */
    @Size(max= 255,message="编码长度不能超过255")
    @Schema(description = "头像URL，第三方登录同步，否则走默认")
    @Length(max= 255,message="编码长度不能超过255")
    private String avatar;
    /**
    * 邮箱（用于密码登录或找回，可空）
    */
    @Size(max= 100,message="编码长度不能超过100")
    @Schema(description = "邮箱（用于密码登录或找回，可空）")
    @Length(max= 100,message="编码长度不能超过100")
    private String email;
    /**
    * 手机号（用于密码登录或找回，可空）
    */
    @Size(max= 20,message="编码长度不能超过20")
    @Schema(description = "手机号（用于密码登录或找回，可空）")
    @Length(max= 20,message="编码长度不能超过20")
    private String phone;
    /**
    * 状态（1-正常，0-禁用）
    */
    @NotNull(message="[状态（1-正常，0-禁用）]不能为空")
    @Schema(description = "状态（1-正常，0-禁用）")
    private Integer status;
    /**
    * 是否删除（0-未删除，1-已删除）
    */
    @NotNull(message="[是否删除（0-未删除，1-已删除）]不能为空")
    @Schema(description = "是否删除（0-未删除，1-已删除）")
    private Integer isDeleted;
    /**
    * 创建时间
    */
    @NotNull(message="[创建时间]不能为空")
    @Schema(description = "创建时间")
    private DateTime createTime;
    /**
    * 更新时间
    */
    @NotNull(message="[更新时间]不能为空")
    @Schema(description = "更新时间")
    private DateTime updateTime;


}
