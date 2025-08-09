package com.weilai.model.user.dos;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 用户详细信息表
 * @TableName tb_user_profile
 */
@TableName("tb_user_profile")
@Data
@Schema(description = "用户详细信息表，存储用户的个人资料信息")
public class UserProfile implements Serializable {

    /**
     * 主键（与tb_user.id一致，避免冗余）
     */
    @NotNull(message="[主键（与tb_user.id一致，避免冗余）]不能为空")
    @Schema(description = "主键（与tb_user.id一致，避免冗余）", example = "123456789012345678")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 真实姓名（用于实名认证）
     */
    @Size(max=50, message="真实姓名长度不能超过50")
    @Schema(description = "真实姓名（用于实名认证）", example = "张三", maxLength = 50)
    @Length(max=50, message="真实姓名长度不能超过50")
    private String realName;

    /**
     * 性别（1-男，2-女，0-未知）
     */
    @Schema(description = "性别（1-男，2-女，0-未知）", example = "1", allowableValues = {"0", "1", "2"})
    private Integer gender;

    /**
     * 生日
     */
    @Schema(description = "用户生日", example = "1990-01-01")
    private Date birthday;

    /**
     * 个人简介
     */
    @Size(max=500, message="个人简介长度不能超过500")
    @Schema(description = "个人简介", example = "热爱编程的开发者", maxLength = 500)
    @Length(max=500, message="个人简介长度不能超过500")
    private String introduction;

    /**
     * 所在地区（如“北京市-海淀区”）
     */
    @Size(max=100, message="所在地区长度不能超过100")
    @Schema(description = "所在地区（如“北京市-海淀区”）", example = "北京市-海淀区", maxLength = 100)
    @Length(max=100, message="所在地区长度不能超过100")
    private String region;

    /**
     * 个人网站/博客地址
     */
    @Size(max=255, message="个人网站地址长度不能超过255")
    @Schema(description = "个人网站/博客地址", example = "https://example.com", maxLength = 255)
    @Length(max=255, message="个人网站地址长度不能超过255")
    private String website;

    /**
     * 更新时间
     */
    @NotNull(message="[更新时间]不能为空")
    @Schema(description = "信息更新时间", example = "2023-10-01T12:00:00")
    private LocalDateTime updateTime;

    /**
     * 个人主页背景图片URL
     */
    @Size(max=255, message="背景图片URL长度不能超过255")
    @Schema(description = "个人主页背景图片URL", example = "https://example.com/bg.jpg", maxLength = 255)
    @Length(max=255, message="背景图片URL长度不能超过255")
    private String backgroundImage;

}
