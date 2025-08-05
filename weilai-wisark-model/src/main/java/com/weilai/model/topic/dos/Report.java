package com.weilai.model.topic.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * 举报记录表
 * @TableName report
 */
@Data
@TableName("report")
@Schema(description = "举报记录表")
public class Report implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @NotNull(message = "[ID]不能为空")
    @Schema(description = "ID")
    private Long id;

    /**
     * 举报人ID
     */
    @NotNull(message = "[举报人ID]不能为空")
    @Schema(description = "举报人ID")
    private Long userId;

    /**
     * 目标类型：1-文章，2-评论，3-用户
     */
    @NotNull(message = "[目标类型]不能为空")
    @Schema(description = "目标类型：1-文章，2-评论，3-用户")
    private Integer targetType;

    /**
     * 目标ID
     */
    @NotNull(message = "[目标ID]不能为空")
    @Schema(description = "目标ID")
    private Long targetId;

    /**
     * 举报原因：1-垃圾广告，2-内容违规，3-抄袭，4-人身攻击，5-其他
     */
    @NotNull(message = "[举报原因]不能为空")
    @Schema(description = "举报原因：1-垃圾广告，2-内容违规，3-抄袭，4-人身攻击，5-其他")
    private Integer reasonType;

    /**
     * 详细原因
     */
    @Schema(description = "详细原因")
    private String reason;

    /**
     * 处理状态：0-待处理，1-已处理（违规），2-不处理（正常）
     */
    @NotNull(message = "[处理状态]不能为空")
    @Schema(description = "处理状态：0-待处理，1-已处理（违规），2-不处理（正常）")
    private Integer status;

    /**
     * 处理人ID（管理员）
     */
    @Schema(description = "处理人ID（管理员）")
    private Long handlerId;

    /**
     * 处理时间
     */
    @Schema(description = "处理时间")
    private DateTime handleTime;

    /**
     * 创建时间
     */
    @NotNull(message = "[创建时间]不能为空")
    @Schema(description = "创建时间")
    private DateTime createdAt;

    /**
     * 更新时间
     */
    @NotNull(message = "[更新时间]不能为空")
    @Schema(description = "更新时间")
    private DateTime updatedAt;
}