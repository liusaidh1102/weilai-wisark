package com.weilai.model.user.vos;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
@Data
public class UserProfileVO extends UserVO implements Serializable {

    /*
     * 序列化版本号
     */
    @Serial
    private static final long serialVersionUID = 1L;


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