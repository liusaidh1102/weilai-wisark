package main.test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class Test {
    /**
     * SHA非对称加密算法
     * @param spara
     * @return
     */
    public static String getSHA(String spara) {
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

            return null;
        }
        return sRtn;
    }
    public static void main(String[] args) {
        System.out.println("fEqNCco3Yq9h5ZUglD3CZJT4lBs=".equals("fEqNCco3Yq9h5ZUglD3CZJT4lBs="));
    }

}
