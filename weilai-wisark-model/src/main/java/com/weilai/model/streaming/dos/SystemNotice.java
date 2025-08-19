package com.weilai.model.streaming.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * 系统通知表
 * @TableName system_notice
 */
@Data
@TableName("system_notice")
@Schema(description = "系统通知表")
public class SystemNotice implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @NotNull(message = "[ID]不能为空")
    @Schema(description = "ID")
    private Long id;

    /**
     * 接收用户ID
     */
    @NotNull(message = "[接收用户ID]不能为空")
    @Schema(description = "接收用户ID")
    private Long userId;

    /**
     * 通知标题
     */
    @NotNull(message = "[通知标题]不能为空")
    @Schema(description = "通知标题")
    private String title;

    /**
     * 通知内容
     */
    @NotNull(message = "[通知内容]不能为空")
    @Schema(description = "通知内容")
    private String content;

    /**
     * 类型：1-开播提醒，2-预约提醒，3-系统公告
     */
    @NotNull(message = "[类型]不能为空")
    @Schema(description = "类型：1-开播提醒，2-预约提醒，3-系统公告")
    private Integer noticeType;

    /**
     * 关联ID（如直播ID）
     */
    @Schema(description = "关联ID（如直播ID）")
    private Long relatedId;

    /**
     * 是否已读：0-否，1-是
     */
    @NotNull(message = "[是否已读]不能为空")
    @Schema(description = "是否已读：0-否，1-是")
    private Integer isRead;

    /**
     * 创建时间
     */
    @NotNull(message = "[创建时间]不能为空")
    @Schema(description = "创建时间")
    private DateTime createdAt;
}