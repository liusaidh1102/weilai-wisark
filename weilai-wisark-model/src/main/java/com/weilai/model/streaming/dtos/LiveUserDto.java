package com.weilai.model.streaming.dtos;

import com.weilai.model.streaming.enums.StreamTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;

@Data
@Schema(description = "直播用户信息")
public class LiveUserDto {
    @Schema(description = "用户id")
    private String userId;
    @Schema(description = "用户昵称")
    private String nickName;
    @Schema(description = "用户头像")
    private String avatar;
    @Schema(description = "房间号")
    private String roomId;
    @Schema(description = "socketId")
    private String socketId;
    @Schema(description = "存储不同流类型的连接映射")
    // 存储不同流类型的连接映射（对应Node.js中的streamConnections）
    private Map<StreamTypeEnum, Map<String, String>> streamConnections = new ConcurrentHashMap<>();
    // 用于防抖处理的定时器（离开房间延迟）
    @Schema(description = "离开房间延迟")
    private Timer leaveTimer;
    @Schema(description = "主播/观众")
    private String role;

}
