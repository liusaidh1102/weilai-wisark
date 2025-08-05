package com.weilai.model.streaming.dos;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

/**
 * 直播房间表
 * @TableName live_room
 */
@Data
@TableName("live_room")
@Schema(description = "直播房间表")
public class LiveRoom implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 房间ID
     */
    @NotNull(message = "[房间ID]不能为空")
    @Schema(description = "房间ID")
    private Long id;

    /**
     * 主播用户ID
     */
    @NotNull(message = "[主播用户ID]不能为空")
    @Schema(description = "主播用户ID")
    private Long userId;

    /**
     * 直播标题
     */
    @NotNull(message = "[直播标题]不能为空")
    @Schema(description = "直播标题")
    private String title;

    /**
     * 封面图URL
     */
    @Schema(description = "封面图URL")
    private String coverImage;

    /**
     * 直播简介
     */
    @Schema(description = "直播简介")
    private String description;

    /**
     * 状态：0-未开始，1-直播中，2-已结束，3-已取消
     */
    @NotNull(message = "[状态]不能为空")
    @Schema(description = "状态：0-未开始，1-直播中，2-已结束，3-已取消")
    private Integer status;

    /**
     * 计划开始时间
     */
    @Schema(description = "计划开始时间")
    private DateTime startTime;

    /**
     * 实际开始时间
     */
    @Schema(description = "实际开始时间")
    private DateTime actualStartTime;

    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    private DateTime endTime;

    /**
     * 观看人数
     */
    @NotNull(message = "[观看人数]不能为空")
    @Schema(description = "观看人数")
    private Integer viewCount;

    /**
     * 点赞数
     */
    @NotNull(message = "[点赞数]不能为空")
    @Schema(description = "点赞数")
    private Integer likeCount;

    /**
     * 分享数
     */
    @NotNull(message = "[分享数]不能为空")
    @Schema(description = "分享数")
    private Integer shareCount;

    /**
     * 是否推荐：0-否，1-是
     */
    @NotNull(message = "[是否推荐]不能为空")
    @Schema(description = "是否推荐：0-否，1-是")
    private Integer isRecommended;

    /**
     * 回放视频URL
     */
    @Schema(description = "回放视频URL")
    private String replayUrl;

    /**
     * 直播时长(秒)
     */
    @Schema(description = "直播时长(秒)")
    private Integer duration;

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

    /**
     * 删除时间
     */
    @Schema(description = "删除时间")
    private DateTime deletedAt;
}