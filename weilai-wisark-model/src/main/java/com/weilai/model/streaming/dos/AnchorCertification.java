package com.weilai.model.streaming.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * 主播认证表
 * @TableName anchor_certification
 */
@Data
@TableName("anchor_certification")
@Schema(description = "主播认证表")
public class AnchorCertification implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @NotNull(message = "[ID]不能为空")
    @Schema(description = "ID")
    private Long id;

    /**
     * 用户ID
     */
    @NotNull(message = "[用户ID]不能为空")
    @Schema(description = "用户ID")
    private Long userId;

    /**
     * 真实姓名
     */
    @NotNull(message = "[真实姓名]不能为空")
    @Schema(description = "真实姓名")
    private String realName;

    /**
     * 身份证号
     */
    @Schema(description = "身份证号")
    private String idCard;

    /**
     * 头衔（如：Java高级工程师）
     */
    @NotNull(message = "[头衔]不能为空")
    @Schema(description = "头衔（如：Java高级工程师）")
    private String title;

    /**
     * 所属机构
     */
    @Schema(description = "所属机构")
    private String organization;

    /**
     * 个人简介
     */
    @Schema(description = "个人简介")
    private String introduction;

    /**
     * 状态：0-待审核，1-已认证，2-已拒绝
     */
    @NotNull(message = "[状态]不能为空")
    @Schema(description = "状态：0-待审核，1-已认证，2-已拒绝")
    private Integer status;

    /**
     * 拒绝原因
     */
    @Schema(description = "拒绝原因")
    private String rejectReason;

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