package cn.luosonglin.test.base.util;


import java.security.*;

/**
 * 类型描述：MD5加密
 * @author 杨康
 * @date 日期：2016-5-3  时间：下午3:15:39
 * @version 1.0
 */
public class MD5Gen {

    public static void main(String[] args) {
        System.out.println(MD5Gen.getMD5("123456"));//e10adc3949ba59abbe56e057f20f883e
        System.out.println(MD5Gen.getMD5("e10adc3949ba59abbe56e057f20f883e"));
    }

    public MD5Gen() {
    }
    
    /**
     * MD5加密
     * 
     * @param src
     * @return
     */
    public static String getMD5(String src) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(src.getBytes());
            byte[] s=m.digest();

            return bintoascii(s);
        } catch (NoSuchAlgorithmException ex) {
            return ex.getMessage();
        }
    }

    public static String bintoascii(byte[] bySourceByte) {
        int len, i;
        byte tb;
        char high, tmp, low;
        String result = new String();
        len = bySourceByte.length;
        for (i = 0; i < len; i++) {
            tb = bySourceByte[i];

            tmp = (char) ((tb >>> 4) & 0x000f);
            if (tmp >= 10) {
                high = (char) ('a' + tmp - 10);
            } else {
                high = (char) ('0' + tmp);
            }
            result += high;
            tmp = (char) (tb & 0x000f);
            if (tmp >= 10) {
                low = (char) ('a' + tmp - 10);
            } else {
                low = (char) ('0' + tmp);
            }

            result += low;
        }
        return result;
    }

}
