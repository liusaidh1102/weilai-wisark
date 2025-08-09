package com.weilai.model.user.vos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UserVO {

    /*
     * 序列化版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户唯一ID（雪花算法生成）
     */
    private Long id;

    /**
     * 用户昵称（第三方登录时同步，否则走默认，必填）
     */
    private String nickname;
    /**
     * 头像URL，第三方登录同步，否则走默认
     */
    private String avatar;
    /**
     * 邮箱（用于密码登录或找回，可空）
     */
    private String email;
    /**
     * 手机号（用于密码登录或找回，可空）
     */
    private String phone;


    /**
     * 性别（1-男，2-女，0-未知）
     */
    @Schema(description = "性别（1-男，2-女，0-未知）", example = "1", allowableValues = {"0", "1", "2"})
    private Integer gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 个人简介
     */
    private String introduction;

    /**
     * 所在地区（如“北京市-海淀区”）
     */
    private String region;

    /**
     * 个人网站/博客地址
     */
    private String website;


    /**
     * 个人主页背景图片URL
     */
    private String backgroundImage;

}