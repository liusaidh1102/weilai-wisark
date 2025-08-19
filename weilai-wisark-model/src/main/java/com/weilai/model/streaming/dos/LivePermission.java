package com.weilai.model.streaming.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * 直播权限配置表
 * @TableName live_permission
 */
@Data
@TableName("live_permission")
@Schema(description = "直播权限配置表")
public class LivePermission implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @NotNull(message = "[ID]不能为空")
    @Schema(description = "ID")
    private Long id;

    /**
     * 直播ID
     */
    @NotNull(message = "[直播ID]不能为空")
    @Schema(description = "直播ID")
    private Long liveId;

    /**
     * 权限类型：0-公开，1-仅粉丝，2-密码访问，3-指定用户
     */
    @NotNull(message = "[权限类型]不能为空")
    @Schema(description = "权限类型：0-公开，1-仅粉丝，2-密码访问，3-指定用户")
    private Integer permissionType;

    /**
     * 访问密码（类型2时必填）
     */
    @Schema(description = "访问密码（类型2时必填）")
    private String password;

    /**
     * 创建时间
     */
    @NotNull(message = "[创建时间]不能为空")
    @Schema(description = "创建时间")
    private DateTime createdAt;
}