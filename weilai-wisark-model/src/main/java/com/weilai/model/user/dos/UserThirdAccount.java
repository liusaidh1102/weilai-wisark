package com.weilai.model.user.dos;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**

 第三方登录关联表
 @TableName tb_user_third_account
 */
@Data
@TableName("tb_user_third_account")
@Schema (description = "第三方登录关联表，存储用户与第三方平台的绑定信息")
public class UserThirdAccount implements Serializable {
    /**
     主键
     */
    @NotNull (message = "[主键] 不能为空")
    @Schema (description = "记录唯一标识（主键）")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     关联的用户 ID（关联 user 表 id）
     */
    @NotNull (message = "[关联的用户 ID（关联 user 表 id）] 不能为空")
    @Schema (description = "关联的系统用户 ID，与用户表主键关联")
    private Long userId;
    /**
     第三方平台类型（1 - 微信，2-QQ，3-GitHub）
     */
    @NotNull (message = "[第三方平台类型（1 - 微信，2-QQ，3-GitHub）] 不能为空")
    @Schema (description = "第三方平台类型  1 - 微信\", \"2-QQ\", \"3-GitHub")
    private Integer thirdType;
    /**
     第三方平台的用户唯一标识（如微信的 openid，GitHub 的 id，平台内唯一，注销账号的 openid 可能被复用）
     */
    @NotBlank (message = "[第三方平台的用户唯一标识] 不能为空")
    @Size (max = 100, message = "第三方标识长度不能超过 100")
    @Length (max = 100, message = "第三方标识长度不能超过 100")
    @Schema (
            description = "第三方平台的用户唯一标识（如微信 openid、GitHub id）",
            maxLength = 100
    )
    private String thirdOpenId;
    /**
     第三方平台的全局唯一标识（如微信的 unionid，跨应用通用，跨平台唯一，微信和 qq 优先使用 union_id）
     */
    @Size (max = 100, message = "全局标识长度不能超过 100")
    @Length (max = 100, message = "全局标识长度不能超过 100")
    @Schema (
            description = "第三方平台的全局唯一标识（如微信 unionid，跨应用通用）",
            maxLength = 100
    )
    private String thirdUnionId;
    /**
     第三方平台的访问令牌（可选，用于后续获取用户信息），加密
     */
    @Size (max = 255, message = "访问令牌长度不能超过 255")
    @Length (max = 255, message = "访问令牌长度不能超过 255")
    @Schema (
            description = "第三方平台的访问令牌（加密存储，用于后续获取用户信息）",
            maxLength = 255
    )
    private String accessToken;
    /**
     第三方平台刷新访问令牌，加密
     */
    @Size (max = 255, message = "刷新令牌长度不能超过 255")
    @Length (max = 255, message = "刷新令牌长度不能超过 255")
    @Schema (
            description = "第三方平台的刷新令牌（加密存储，用于刷新访问令牌）",
            maxLength = 255
    )
    private String refreshToken;
    /**
     令牌过期时间
     */
    @Schema (description = "访问令牌的过期时间")
    private Date expireTime;
    /**
     是否解绑（0 - 绑定中，1 - 已解绑）
     */
    @NotNull (message = "[是否解绑（0 - 绑定中，1 - 已解绑）] 不能为空")
    @Schema (description = "绑定状态标识  0 - 绑定中\", \"1 - 已解绑")
    private Integer isUnbind;
    /**
     解绑时间（is_unbind=1 时生效）
     */
    @Schema (description = "解绑操作的时间（仅当 isUnbind=1 时有效）")
    private Date unbindTime;
    /**
     绑定时间
     */
    @NotNull (message = "[绑定时间] 不能为空")
    @Schema (description = "第三方账号与系统用户的绑定时间")
    private Date createTime;
    /**
     更新时间
     */
    @NotNull (message = "[更新时间] 不能为空")
    @Schema (description = "记录最后更新时间（如令牌刷新、状态变更时）")
    private Date updateTime;
}