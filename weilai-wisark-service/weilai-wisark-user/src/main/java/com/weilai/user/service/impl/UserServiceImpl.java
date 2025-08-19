package com.weilai.user.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.stp.parameter.SaLoginParameter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.weilai.common.response.Result;
import com.weilai.common.utils.SHAUtil;
import com.weilai.model.user.dos.UserProfile;
import com.weilai.model.user.dos.UserThirdAccount;
import com.weilai.model.user.dtos.EmailLoginDTO;
import com.weilai.user.mapper.UserAccountMapper;
import com.weilai.user.mapper.UserProfileMapper;
import com.weilai.user.service.UserService;
import com.weilai.user.mapper.UserMapper;
import com.weilai.model.user.dos.User;
import com.weilai.user.wechat.TokenInfo;
import com.weilai.user.wechat.WechatApp;
import com.weilai.user.wechat.WechatUser;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

import static com.weilai.common.constants.CacheConstant.*;
import static com.weilai.common.response.CodeEnum.*;

@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Resource
    private UserMapper userMapper;

    @Resource
    private UserProfileMapper userProfileMapper;

    @Resource
    private WechatApp wechatApp;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private UserAccountMapper userAccountMapper;

    @Resource
    private SHAUtil shaUtil;


    /**
     * 邮箱登录
     * @param email
     * @param code
     * @return
     */
    @Transactional
    @Override
    public Result<?> loginByCode(String email, String code) {
        if (StrUtil.isBlank(email) || StrUtil.isBlank(code)) {
            return Result.fail(BAD_REQUEST);
        }
        String key = EMAIL_CODE_PREFIX + email;
        String redisCode = stringRedisTemplate.opsForValue().get(key);
        if (StrUtil.isBlank(redisCode) && !code.equals(redisCode)) {
            return Result.fail(ERROR_CODE);
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, email);
        User user = userMapper.selectOne(queryWrapper);
        Long userId = null;
        // 新用户
        if (user == null) {
            // 用户基本信息
            User insertUser = new User();
            insertUser.setEmail(email);
            insertUser.setNickname(email);
            userMapper.insert(insertUser);
            // 用户额外信息
            UserProfile userProfile = new UserProfile();
            userId = insertUser.getId();
            userProfile.setId(userId);
            userProfileMapper.insert(userProfile);
        }else{
            userId = user.getId();
        }
        String token = getToken(userId);
        return Result.ok(token);
    }

    /**
     * 密码登录
     * @param
     * @param
     * @return
     */
    @Override
    public Result<?> loginByPwd(EmailLoginDTO emailLoginDTO) {
        String email = emailLoginDTO.getEmail();
        String pwd = emailLoginDTO.getPwd();
        if (StrUtil.isBlank(email) || StrUtil.isBlank(pwd)) {
            return Result.fail(BAD_REQUEST);
        }
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getEmail, email);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            return Result.fail(ERROR_PWD);
        }
        String password = user.getPassword();
        if (StrUtil.isBlank(password)){
            return Result.fail(ERROR_PWD);
        }
        String shaPwd = shaUtil.getSHA(pwd);
        // 加密失败
        if (shaPwd == null){
            return Result.fail();
        }
        if (!shaPwd.equals(password)){
            return Result.fail(ERROR_PWD);
        }
        Long userId = user.getId();
        // 登录
        String token = getToken(userId);
        return Result.ok(token);
    }

    @Transactional
    @Override
    public Result<?> wechatLoginCallback(String code, String state) {
        WechatUser wechatUser = getWechatUser(code, state);
        // 获取第三方用户信息
        String openId = wechatUser.getOpenid();
        LambdaQueryWrapper<UserThirdAccount> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 状态为绑定的
        lambdaQueryWrapper.eq(UserThirdAccount::getThirdOpenId, openId).eq(UserThirdAccount::getIsUnbind, 0);
        UserThirdAccount userThirdAccount = userAccountMapper.selectOne(lambdaQueryWrapper);
        Long userId = null;
        if(userThirdAccount == null){
            // 插入用户
            User insertUser = new User();
            insertUser.setNickname(wechatUser.getNickname());
            insertUser.setAvatar(wechatUser.getHeadimgurl());
            userMapper.insert(insertUser);
            userId = insertUser.getId();
            UserProfile userProfile = new UserProfile();
            userProfile.setId(userId);
            userProfile.setGender(Integer.valueOf(wechatUser.getSex()));
            userProfileMapper.insert(userProfile);
            userThirdAccount = new UserThirdAccount();
            userThirdAccount.setUserId(userId);
            userThirdAccount.setThirdOpenId(openId);
            userThirdAccount.setThirdType(1);
            userAccountMapper.insert(userThirdAccount);
        }
        userId = userThirdAccount.getUserId();
        // 登录成功
        log.debug("用户{}通过微信登录", userId);
        return Result.ok(getToken(userId));
    }


    /**
     *  登录，返回token
     * @param userId
     * @return
     */
    private String getToken(Long userId) {
        // 登录
        StpUtil.login(userId,new SaLoginParameter().setExtra("id", userId.toString()));
        String token = StpUtil.getTokenValue();
        String tokenKey = LOGIN_TOKEN_PREFIX + token;
        stringRedisTemplate.opsForValue().set(tokenKey,userId.toString(),LOGIN_TOKEN_EXPIRE, TimeUnit.SECONDS);
        return token;
    }


    /**
     *  获取用户信息
     * @param code
     * @param state
     * @return
     */
    private WechatUser getWechatUser(String code, String state) {
        log.info("进入callback方法,code={},state={}", code, state);
        //获取code后，请求以下链接获取access_token：
        String url = String.format(
                "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code",
                wechatApp.getAppId(),
                wechatApp.getAppSecret(),
                code);
        //请求url
        String infoStr = restTemplate.getForObject(url, String.class);

        // 转换为bean
        TokenInfo tokenInfo = JSONUtil.toBean(infoStr, TokenInfo.class);
        String access_token = tokenInfo.getAccess_token();
        String openid = tokenInfo.getOpenid();

        // 拉取用户信息
        String userStr = "https://api.weixin.qq.com/sns/userinfo?access_token=" + access_token + "&openid=" + openid + "&lang=zh_CN";
        String userInfo = restTemplate.getForObject(userStr, String.class);
        // 获取到用户的信息
        return JSONUtil.toBean(userInfo, WechatUser.class);
    }
}