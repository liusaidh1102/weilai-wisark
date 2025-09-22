package com.weilai.model.user.vos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class UserVO {

    @Schema(description = "用户id")
    private Long id;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户头像")
    private String avatar;

    @Schema(description = "性别,0默认，1男，2女", allowableValues = {"0", "1", "2"})
    private Integer gender;

    @Schema(description = "生日")
    private Date birthday;

    @Schema(description = "简介")
    private String introduction;

    @Schema(description = "地区")
    private String region;

    @Schema(description = "个人网站")
    private String website;

    @Schema(description = "背景图片")
    private String backgroundImage;

}