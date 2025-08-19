package com.weilai.common.utils;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
@Component
@Slf4j
public class EmailUtil {




    /**
     * 发送验证码邮件并返回验证码
     * @param email 收件人邮箱
     * @return 生成的验证码
     * @throws Exception 邮件发送异常
     */
    public String getCode(String email,JavaMailSender javaMailSender) {
        String code = null;
        try {
            // 生成6位数字验证码（原UUID方式可能包含字母，这里改为纯数字）
            code = String.valueOf((int)((Math.random() * 9 + 1) * 100000));
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            // 设置收件人
            helper.setTo(email);
            // 设置邮件主题
            helper.setSubject("【智慧云舟】验证码通知");
            // 设置邮件内容（HTML格式）
            String content = "<div style='font-family: Arial, sans-serif; max-width: 600px; margin: 0 auto; padding: 20px;'>"
                    + "<h2 style='color: #333;'>您的验证码是：</h2>"
                    + "<div style='font-size: 24px; font-weight: bold; color: #2c3e50; margin: 20px 0;'>" + code + "</div>"
                    + "<p style='color: #666;'>该验证码有效期为5分钟，请尽快使用。</p>"
                    + "<p style='color: #999; font-size: 12px; margin-top: 30px;'>本邮件为系统自动发送，请勿回复。</p>"
                    + "</div>";
            helper.setText(content, true);
            // 发送邮件
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            log.error("发送邮件失败：{}", e.getMessage());
            return null;
        }
        return code;
    }
}