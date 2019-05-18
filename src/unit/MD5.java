package unit;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    /**
     * 加密算法
     * @param plainText
     * @return
     */
    public static String md5(String plainText) {
        byte[] secretBytes = null;
        try {
            //获取md5算法->计算->获取结果
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有md5这个算法！");
        }
        //转16进制
        String md5code = new BigInteger(1, secretBytes).toString(16);
        //不足位补0
        for ( ; md5code.length() < 32 ; md5code = "0" + md5code) { }
        return md5code;
    }
}
