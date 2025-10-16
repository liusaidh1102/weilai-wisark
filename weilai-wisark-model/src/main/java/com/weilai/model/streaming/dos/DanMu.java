package com.weilai.model.streaming.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@TableName("danmu")
@Schema(description = "弹幕表的内容")
public class DanMu implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(description = "ID")
    private String id;
    @Schema(description = "弹幕内容")
    @NotNull(message = "[弹幕内容]不能为空")
    private String content;
    @Schema(description = "弹幕发送时间")
    @NotNull(message = "[弹幕发送时间]不能为空")
    private String sendTime;
    @Schema(description = "弹幕发送者ID")
    @NotNull(message = "[弹幕发送者ID]不能为空")
    private String userId;
    @Schema(description = "弹幕结束时间")
    @NotNull(message = "[弹幕结束时间]不能为空")
    private String endTime;
    @Schema(description = "弹幕的视频id（也就是房间id）")
    @NotNull(message = "[弹幕的视频id（也就是房间id）]不能为空")
    private String roomId;
}
