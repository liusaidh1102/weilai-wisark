package com.weilai.model.streaming.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * 直播内容审核表
 * @TableName live_audit
 */
@Data
@TableName("live_audit")
@Schema(description = "直播内容审核表")
public class LiveAudit implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @NotNull(message = "[ID]不能为空")
    @Schema(description = "ID")
    private Long id;

    /**
     * 关联直播间
     */
    @Schema(description = "关联直播间")
    private Long liveId;

    /**
     * 关联违规消息（live_message.id）
     */
    @Schema(description = "关联违规消息（live_message.id）")
    private Long messageId;

    /**
     * 审核类型：1-直播间标题/封面，2-互动消息
     */
    @NotNull(message = "[审核类型]不能为空")
    @Schema(description = "审核类型：1-直播间标题/封面，2-互动消息")
    private Integer auditType;

    /**
     * 违规内容
     */
    @NotNull(message = "[违规内容]不能为空")
    @Schema(description = "违规内容")
    private String content;

    /**
     * 状态：0-待审核，1-通过，2-违规
     */
    @NotNull(message = "[状态]不能为空")
    @Schema(description = "状态：0-待审核，1-通过，2-违规")
    private Integer status;

    /**
     * 审核人ID（管理员）
     */
    @Schema(description = "审核人ID（管理员）")
    private Long reviewerId;

    /**
     * 创建时间
     */
    @NotNull(message = "[创建时间]不能为空")
    @Schema(description = "创建时间")
    private DateTime createdAt;
}