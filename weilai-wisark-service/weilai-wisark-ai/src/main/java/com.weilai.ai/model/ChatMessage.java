package com.weilai.ai.model;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.MessageType;

/**
* AI聊天消息表
* @TableName t_chat_message
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "t_chat_message")
public class ChatMessage implements Serializable {

    /**
    * 主键ID
    */
    @NotNull(message="[主键ID]不能为空")
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
    * 会话ID
    */
    @Size(max= 255,message="编码长度不能超过255")
    @Length(max= 255,message="编码长度不能超过255")
    private String cid;
    /**
    * 消息内容
    */
    @Size(max= -1,message="编码长度不能超过-1")
    @Length(max= -1,message="编码长度不能超过-1")
    private String content;
    /**
    * 消息类型(TEXT/IMAGE/AUDIO/FILE)
    */
    @Size(max= 50,message="编码长度不能超过50")
    @Length(max= 50,message="编码长度不能超过50")
    private String messageType;
    /**
    * 使用的AI模型
    */
    @Size(max= 100,message="编码长度不能超过100")
    @Length(max= 100,message="编码长度不能超过100")
    private String model;
    /**
    * 消息消耗的token数量
    */
    private Integer tokens;
    /**
    * 是否删除(0:否, 1:是)
    */
    private Integer isDeleted;
    /**
    * 创建时间
    */
    @NotNull(message="[创建时间]不能为空")
    private Date createTime;
    /**
    * 更新时间
    */
    @NotNull(message="[更新时间]不能为空")
    private Date updateTime;

}
