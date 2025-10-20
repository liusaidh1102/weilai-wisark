package com.weilai.user.controller;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.weilai.common.response.Result;
import com.weilai.common.utils.EmailUtil;
import com.weilai.model.user.dtos.CodeLoginDTO;
import com.weilai.model.user.dtos.EmailLoginDTO;
import com.weilai.user.service.UserService;
import com.weilai.user.wechat.WechatApp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import static com.weilai.common.constants.CacheConstant.EMAIL_CODE_EXPIRE;
import static com.weilai.common.constants.CacheConstant.EMAIL_CODE_PREFIX;
import static com.weilai.common.response.CodeEnum.*;

/**
 * 认证控制器：处理登录、注册相关接口
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "认证接口", description = "包含第三方登录、手机号登录、注册等功能")
@Slf4j
public class AuthController {

    @Resource
    private UserService userService;

    @Resource
    private EmailUtil emailUtil;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private WechatApp wechatApp;


    /**
     * 发送邮箱验证码
     *
     * @param email 邮箱
     * @return 发送结果
     */
    @PostMapping("/email/code/{email}")
    @Operation(summary = "发送邮箱验证码", description = "向指定邮箱发送登录/注册验证码")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "操作失败"),
            @ApiResponse(responseCode = "600", description = "请勿重复发送验证码"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public Result<?> sendVerificationCode(@PathVariable String email) {
        if (StrUtil.isBlank(email)) {
            return Result.fail(BAD_REQUEST);
        }
        String key = EMAIL_CODE_PREFIX + email;
        String redisCode = stringRedisTemplate.opsForValue().get(key);
        if (StrUtil.isNotBlank(redisCode)) {
            return Result.fail(CODE_EXISTS);
        }
        // code存redis
        String code = emailUtil.getCode(email, javaMailSender);
        if (code == null) {
            return Result.fail();
        }
        stringRedisTemplate.opsForValue().set(key, code, EMAIL_CODE_EXPIRE, TimeUnit.SECONDS);
        return Result.ok();
    }

    /**
     * 邮箱验证码登录, 若无账号则自动登录
     *
     * @return 登录结果（令牌 ）
     */
    @PostMapping("/code/login")
    @Operation(summary = "邮箱验证码登录", description = "通过邮箱 + 验证码完成登录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "操作失败"),
            @ApiResponse(responseCode = "601", description = "邮箱或验证码错误"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public Result<?> loginByCode(CodeLoginDTO codeLoginDTO) {
        return userService.loginByCode(codeLoginDTO.getEmail(), codeLoginDTO.getCode());
    }

    /**
     * 邮箱 + 密码 登录
     *
     * @return 登录结果（令牌）
     */
    @PostMapping("/email/login")
    @Operation(summary = "邮箱密码登录", description = "通过邮箱 + 密码")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "操作失败"),
            @ApiResponse(responseCode = "602", description = "密码错误"),
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public Result<?> loginByPwd(@RequestBody EmailLoginDTO emailLogin) {
        return userService.loginByPwd(emailLogin);
    }


    @GetMapping("/wechat/check")
    @Operation(hidden = true)
    public String wxSignatureCheck(
            @RequestParam(value = "signature") String signature,
            @RequestParam(value = "timestamp") String timestamp,
            @RequestParam(value = "nonce") String nonce,
            @RequestParam(value = "echostr") String echostr) {
        log.info("signature:{},timestamp:{},nonce:{},echostr:{}", signature, timestamp, nonce, echostr);

        // 使用Hutool排序和加密
        ArrayList<String> list = new ArrayList<>();
        list.add(nonce);
        list.add(timestamp);
        list.add(wechatApp.getToken()); // token

        // 排序
        list.sort(String::compareTo);

        // 拼接字符串
        String decode = String.join("", list);

        // SHA1加密（使用Hutool）
        String encode = DigestUtil.sha1Hex(decode);

        log.info("encode:{}", encode);

        if (encode.equals(signature)) {
            return echostr;
        }
        return "";
    }

    /**
     * 微信登录
     *
     * @ return登录结果（令牌 ）
     */
    @SaIgnore
    @PostMapping("/wechat/login")
    @Operation(summary = "微信登录", description = "通过微信登录")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "服务器内部错误")
    })
    public Result<?> loginByWechat() {
        try {
            // 使用 Hutool 进行 URL 编码
            String encodedRedirectUrl = URLUtil.encode(wechatApp.getRedirectUri());

            // 使用 String.format 构建 URL
            String url = String.format(
                    "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=%s&scope=%s&state=STATE#wechat_redirect",
                    wechatApp.getAppId(),
                    encodedRedirectUrl,
                    wechatApp.getResponseType(),
                    wechatApp.getScope()
            );

            log.info("url:{}", url);


//            String[] supportedFormats = ImageIO.getWriterFormatNames();
//            log.info("当前环境支持的图片格式：{}", Arrays.toString(supportedFormats));

            // 生成二维码并转换为Base64
            String base64QRCode = QrCodeUtil.generateAsBase64(url, new QrConfig(300, 300), "png");
            return Result.ok(base64QRCode);
        } catch (Exception e) {
            log.error("生成微信登录二维码失败", e);
            return Result.fail();
        }
    }

    /**
     * QQ 登录
     * @return  返回base64二维码
     */
    @PostMapping("/qq/login")
    @Operation(summary = "qq登录", description = "通过qq登录")
    public Result<?> loginByQQ() {
        return Result.fail();
    }

    /**
     * Github 登录
     * @return  返回base64二维码
     */
    @PostMapping("/github/login")
    @Operation(summary = "github登录", description = "通过github登录")
    public Result<?> loginByGithub() {
        return Result.fail();
    }



    @RequestMapping("/wechat/callback")
    @Operation(hidden = true)
    public Result<?> wxLoginCallback(String code, String state) {
        return userService.wechatLoginCallback(code, state);
    }


    /**
     * 退出登录
     * @return
     */
    @PostMapping("/logout")
    public Result<?> logout() {
        if (!StpUtil.isLogin()){
            return Result.fail(UNAUTHORIZED);
        }
        StpUtil.logout();
        return Result.ok();
    }

}