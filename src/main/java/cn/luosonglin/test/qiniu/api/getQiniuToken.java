package cn.luosonglin.test.qiniu.api;

import cn.luosonglin.test.qiniu.config.Config;
import com.qiniu.util.Auth;

/**
 * Created by luosonglin on 23/12/2016.
 */
public class getQiniuToken {
    public static void main(String[]args) {
        Auth auth = Auth.create(Config.ACCESS_KEY, Config.SECRET_KEY);
        String bucketName = "test";
        String token = auth.uploadToken(bucketName);
        System.out.println(token);

        System.out.print(auth.uploadToken(bucketName, "0.jpg"));
    }
}
