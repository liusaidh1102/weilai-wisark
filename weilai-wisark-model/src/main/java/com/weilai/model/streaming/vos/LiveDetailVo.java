package com.weilai.model.streaming.vos;

import com.weilai.model.user.vos.UserVO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class LiveDetailVo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 房间ID
     */

    @Schema(description = "房间ID")
    private String id;

    /**
     * 用户信息
     */
    @Schema(description = "用户信息")
    private UserVO userInfo;


    /**
     * 直播标题
     */
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

    @Schema(description = "状态：0-未开始，1-直播中，2-已结束，3-已取消")
    private Integer status;

    /**
     * 计划开始时间
     */
    @Schema(description = "计划开始时间")
    private LocalDateTime startTime;

    /**
     * 实际开始时间
     */
    @Schema(description = "实际开始时间")
    private LocalDateTime actualStartTime;

    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    private LocalDateTime endTime;

    /**
     * 观看人数
     */
    @Schema(description = "观看人数")
    private Integer viewCount;

    /**
     * 点赞数
     */
    @Schema(description = "点赞数")
    private Integer likeCount;

    /**
     * 分享数
     */
    @Schema(description = "分享数")
    private Integer shareCount;

    /**
     * 是否推荐：0-否，1-是
     */
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
    @Schema(description = "创建时间")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updatedAt;

    /**
     * 删除时间
     */
    @Schema(description = "删除时间")
    private LocalDateTime deletedAt;
}
