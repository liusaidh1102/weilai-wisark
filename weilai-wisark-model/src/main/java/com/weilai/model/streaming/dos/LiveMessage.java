package com.weilai.model.streaming.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * 直播互动消息表
 * @TableName live_message
 */
@Data
@TableName("live_message")
@Schema(description = "直播互动消息表")
public class LiveMessage implements Serializable {

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
     * 发送用户ID
     */
    @NotNull(message = "[发送用户ID]不能为空")
    @Schema(description = "发送用户ID")
    private Long userId;

    /**
     * 消息内容
     */
    @NotNull(message = "[消息内容]不能为空")
    @Schema(description = "消息内容")
    private String content;

    /**
     * 图片URL
     */
    @Schema(description = "图片URL")
    private String imageUrl;

    /**
     * 消息点赞数
     */
    @NotNull(message = "[消息点赞数]不能为空")
    @Schema(description = "消息点赞数")
    private Integer likeCount;

    /**
     * 是否被主播置顶：0-否，1-是
     */
    @NotNull(message = "[是否被主播置顶]不能为空")
    @Schema(description = "是否被主播置顶：0-否，1-是")
    private Integer isPinned;

    /**
     * 创建时间
     */
    @NotNull(message = "[创建时间]不能为空")
    @Schema(description = "创建时间")
    private DateTime createdAt;

    /**
     * 删除时间
     */
    @Schema(description = "删除时间")
    private DateTime deletedAt;
}