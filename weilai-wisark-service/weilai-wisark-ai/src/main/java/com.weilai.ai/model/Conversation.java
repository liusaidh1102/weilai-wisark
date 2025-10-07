package com.weilai.ai.model;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
/**
 * 会话表
 * @TableName t_conversation
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_conversation")
public class Conversation implements Serializable {


    /**
    * 主键ID
    */
    @TableId(type = IdType.AUTO)
    private Long id;


    /**
     * 会话ID
     */
    @Size(max= 255,message="编码长度不能超过255")
    @Length(max= 255,message="编码长度不能超过255")
    private String cid;
    /**
     * 用户ID
     */
    @NotNull(message="[用户ID]不能为空")
    private Long uid;
    /**
     * 会话标题
     */
    @Size(max= 500,message="编码长度不能超过500")
    @Length(max= 500,message="编码长度不能超过500")
    private String title;
    /**
     * 使用的AI模型
     */
    @Size(max= 100,message="编码长度不能超过100")
    @Length(max= 100,message="编码长度不能超过100")
    private String model;
    /**
     * 总token消耗
     */
    private Integer totalTokens;
    /**
     * 消息数量
     */
    private Integer messageCount;
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
