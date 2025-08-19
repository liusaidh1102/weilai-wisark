package com.weilai.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

/**
 * SHA非对称加密算法（不可解密）
 */
@Component
@Slf4j
public class SHAUtil {


    /**
     * SHA非对称加密算法
     * @param spara
     * @return
     */
    public String getSHA(String spara) {
        String sRtn = null;
        try {
            byte[] plainText = spara.getBytes(StandardCharsets.UTF_8);

            // 使用getInstance("算法")来获得消息摘要,这里使用SHA-1的160位算法
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            // 开始使用算法
            messageDigest.update(plainText);
            // 输出算法运算结果
            Base64.Encoder encoder = Base64.getMimeEncoder();
            sRtn = encoder.encodeToString(messageDigest.digest());
//			sRtn = new BASE64Encoder().encode(messageDigest.digest());
        } catch (Exception e) {
            log.error("catch exception:{}", e.getMessage());
            return null;
        }
        return sRtn;
    }

}