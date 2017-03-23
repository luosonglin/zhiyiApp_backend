package cn.luosonglin.test.base.util;

import java.io.IOException;

/**
 * Created by luosonglin on 20/03/2017.
 */
public class CodeUtil {


    /**
     * 编码
     * @param bstr
     * @return
     */
    public static String encode(byte[] bstr) {
        return new sun.misc.BASE64Encoder().encode(bstr);
    }

    /**
     * 解码
     * @param str
     * @return
     */
    public static String decode(String str) {
        byte[] bt = null;
        try {
            sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
            bt = decoder.decodeBuffer(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bt.toString();
    }
}
